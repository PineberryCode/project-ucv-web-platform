
const {useState} = React;

const recoverProductData = JSON.parse(localStorage.getItem('ProductDataList'));
let productRow = recoverProductData.split(',');
let productData = [['ID','Category','Nombre','Cantidad','Precio Unitario']];

for (let x of productRow) {
    let id = x.split('-')[0];
    let category = x.split('-')[1];
    let name = x.split('-')[2];
    let quantity = x.split('-')[3];
    let uni_price = x.split('-')[4];

    productData.push([id,category,name,quantity,uni_price]);
}

var sliceProductData = productData.slice(1,productData.length);

const ProductContent = () => {

    const [editMode, setEditMode] = useState(null);
    const [isEditing, setIsEditing] = useState(false);
    const [productData, setProductData] = useState(sliceProductData);

    const handleDoubleClick = (rowIndex, columnIndex) => {
        if (!isEditing && columnIndex !== 0) {
            setEditMode({row:rowIndex, col:columnIndex});
            setIsEditing(true);
        }
    };

    const handleInputChange = (e, rowIndex, columnIndex) => {
        const updatedData = [...productData];
        updatedData[rowIndex][columnIndex] = e.target.value;

        document.cookie=`extractedValueProductOriginal=${encodeURIComponent(e.target.value)}; path=/restricted; max-age=3600; samesite=None; secure;`;
        setProductData(updatedData);

        document.cookie=`extractedColumnProductOriginal=${columnIndex}; path=/restricted; max-age=3600; samesite=None; secure;`;
        
    }

    const handleInputKeyDown = (e) => {
        if (e.key === 'Enter') {
            setEditMode(null);
            //setIsEditing(false);
            document.getElementById("form-update-product").submit();
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
            <h4 className="p-5">Productos</h4>
            <table className="table table-hover">
            <thead className="table-bordered border-primary text-center">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Categoría</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Cantidad</th>
                    <th scope="col">Precio Unitario</th>
                    <th scope="col">Acción</th>
                </tr>
            </thead>
            <tbody>
            {sliceProductData.map((product, rowIndex) => (
                <tr
                key={rowIndex}
                className="text-center"
                >
                {product.map((cell, columnIndex) => (
                    <td key={columnIndex}
                    onDoubleClick={() => handleDoubleClick(rowIndex, columnIndex)}
                    id={`${columnIndex}${rowIndex}`}
                    >
                      {editMode && editMode.row === rowIndex && editMode.col === columnIndex ?
                      (
                        <form
                        id="form-update-product" 
                        action="/restricted/control-panel/update-product" 
                        method="POST">
                            <input 
                            className="form-control"
                            type="text"
                            value={cell}
                            onChange={(e) => handleInputChange(e, rowIndex, columnIndex)}
                            onKeyDown={handleInputKeyDown}
                            onKeyUp={handleInputKeyUp}
                            />
                            <input type="hidden" name="productID" value={product[0]} />
                        </form>
                      ) : (cell)
                      }
                    </td>
                ))}
                <td>
                    <form action="/restricted/control-panel/delete-product" method="POST">
                      <input type="hidden" name="productID" value={product[0]} />
                      <button className="delete btn btn-danger" type="submit">X</button>
                    </form>
                </td>
                </tr>
            ))}
            </tbody>
            </table>
        </>
    );
}