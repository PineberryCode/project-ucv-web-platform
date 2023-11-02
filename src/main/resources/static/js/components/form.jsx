
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

const SupplierForm = () => {
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
        //var value = document.getElementById(columnIndex+""+rowIndex).textContent;
        document.cookie =`extractedValue=${e.target.value}; path=/restricted; max-age=3600;`;
        //console.log("Row:"+rowIndex+" Column: "+columnIndex+": "+e.target.value);
        setSupplierData(updatedData);
        document.cookie=`extractedColumn=${columnIndex}; path=/restricted; max-age=3600;`;
        
    }

    const handleInputKeyDown = (e, rowIndex, columnIndex) => {
        if (e.key === 'Enter') {
            setEditMode(null);
            setIsEditing(false);
        }
        
    }

    const handleInputKeyUp = (e, rowIndex, columnIndex) => {
        if (e.key === 'Enter') {
            setEditMode(null);
            
            setIsEditing(false);
            
        }
    }
    
    return (
        <div>
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
                                    <form action="/restricted/control-panel/update-supplier" method="POST">
                                    <input
                                    className="form-control" 
                                    type="text" 
                                    value={cell} 
                                    onChange={(e) => handleInputChange(e,rowIndex,columnIndex)}
                                    onKeyDown={handleInputKeyDown(rowIndex, columnIndex)}
                                    onKeyUp={handleInputKeyUp(rowIndex, columnIndex)}
                                    />
                                    <input type="hidden" name="supplierID" value={supplier[0]}/>
                                    </form>
                                ) : (cell)
                                }
                            </td>
                        ))}
                        <td>
                        <form action="/restricted/control-panel/delete-supplier" method="POST">
                            <input type="hidden" name="supplierID" value={supplier[0]}/>
                            <button className="delete btn btn-danger" type="submit">X</button>
                        </form>
                        </td>
                    </tr>
                ))}
                <tr className="text-center">
                    <td>
                        <ButtonAddNew />
                    </td>
                </tr>
            </tbody>
            </table>
            
        </div>
        
    );
}
//<Form> only allows GET & POST
const EmployeeForm = () => {
    return (
        <div>
            <h4 className="p-5">Empleados</h4>
            <table className="table table-hover">
            <thead className="table-bordered border-primary text-center">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Rol</th>
                    <th scope="col">E-mail</th>
                    <th scope="col">Names</th>
                    <th scope="col">Lastname</th>
                    <th scope="col">Address</th>
                    <th scope="col">Acción</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row"></th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <form className="text-center" action="/restricted/control-panel/delete" method="POST">
                            <button className="btn btn-danger" type="submit">X</button>
                        </form>
                    </td>
                </tr>
            </tbody>
            </table>
        </div>
        
    );
}