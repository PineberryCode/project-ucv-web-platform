
const {useState} = React;

const recoverSupplierData = JSON.parse(localStorage.getItem('SupplierData'));
let supplierRow = recoverSupplierData.split(',');
let supplierData = [['ID','Name','ContactNumber','E-MAIL','Address']];

for (let x of supplierRow) {
    let id = x.split('-')[0];
    let name = x.split('-')[1];
    let number = x.split('-')[2];
    let email = x.split('-')[3];
    let address = x.split('-')[4];
    supplierData.push([id,name,number,email,address]);
}

var sliceSupplierData = supplierData.slice(1,supplierData.length);

const SupplierContent = () => {
    const [editMode, setEditMode] = useState(null);
    const [isEditing, setIsEditing] = useState(false);
    const [supplierData, setSupplierData] = useState(sliceSupplierData);

    const handleDoubleClick = (rowIndex, columnIndex) => {
        if (!isEditing && columnIndex !== 0) {
            
            setEditMode({row:rowIndex, col:columnIndex});
            
            setIsEditing(true);
        }
        
    };

    const handleInputChange = (e, rowIndex, columnIndex) => {
        const updatedData = [...supplierData];
        updatedData[rowIndex][columnIndex] = e.target.value;
        
        document.cookie=`extractedValueSupplier=${encodeURIComponent(e.target.value)}; path=/restricted; max-age=3600; samesite=None; secure;`;
        setSupplierData(updatedData);
        document.cookie=`extractedColumnSupplier=${columnIndex}; path=/restricted; max-age=3600; samesite=None; secure;`;
        
    }

    const handleInputKeyDown = (e) => {
        if (e.key === 'Enter') {
            setEditMode(null);
            //setIsEditing(false);
            document.getElementById("form-update-supplier").submit();
        }
    }

    const handleInputKeyUp = (e) => {
        if (e.key === 'Enter') {
            setEditMode(null);
            setIsEditing(false);
        }
    }
    
    return (
        <>
            <h4 className="p-5">Proveedores</h4>
            <table className="table table-hover">
            <thead className="table-bordered border-primary text-center">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Número</th>
                    <th scope="col">E-mail</th>
                    <th scope="col">Dirección/Localidad</th>
                    <th scope="col">Acción</th>
                </tr>
            </thead>
            <tbody>
                {sliceSupplierData.map((supplier, rowIndex) => (
                    <tr 
                    key={rowIndex}
                    className="text-center"
                    >
                        {supplier.map((cell, columnIndex) => (
                            <td key={columnIndex} 
                            onDoubleClick={() => handleDoubleClick(rowIndex, columnIndex)}
                            id={`${columnIndex}${rowIndex}`}
                            >
                                {editMode && editMode.row === rowIndex && editMode.col === columnIndex ? 
                                (
                                    <form 
                                    id="form-update-supplier" 
                                    action="/restricted/control-panel/update-supplier" 
                                    method="POST"
                                    >
                                    <input
                                    className="form-control" 
                                    type="text" 
                                    value={cell} 
                                    onChange={(e) => handleInputChange(e,rowIndex,columnIndex)}
                                    onKeyDown={handleInputKeyDown}
                                    onKeyUp={handleInputKeyUp}
                                    />
                                    <input type="hidden" name="supplierID" value={supplier[0]} />
                                    </form>
                                ) : (cell)
                                }
                            </td>
                        ))}
                        <td>
                        <form action="/restricted/control-panel/delete-supplier" method="POST">
                            <input type="hidden" name="supplierID" value={supplier[0]} />
                            <button className="delete btn btn-danger" type="submit">X</button>
                        </form>
                        </td>
                    </tr>
                ))}
                <tr className="text-center">
                    <td>
                        <ButtonAddNewSupplier />
                    </td>
                </tr>
            </tbody>
            </table>  
        </>
        
    );
}