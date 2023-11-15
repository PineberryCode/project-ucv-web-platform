
const {useState} = React;

const recoverEmployeeData = JSON.parse(localStorage.getItem('ListEmployeeTable'));
let employeeRow = recoverEmployeeData.split(',');
let employeeData = [['ID','Role','E-MAIL','Names','Lastname','Address']];

for (let x of employeeRow) {
    let id = x.split('-')[0];
    let role = x.split('-')[1];
    let email = x.split('-')[2];
    let names = x.split('-')[3];
    let lastname = x.split('-')[4];
    let address = x.split('-')[5];
    
    employeeData.push([id,role,email,names,lastname,address]);
}

var sliceEmployeeData = employeeData.slice(1,employeeData.length);

const EmployeeContent = () => {
    const [editMode, setEditMode] = useState(null);
    const [isEditing, setIsEditing] = useState(false);
    const [employeeData, setEmployeeData] = useState(sliceEmployeeData);

    const handleDoubleClick = (rowIndex, columnIndex) => {
        if (!isEditing && columnIndex !== 0) {
            setEditMode({row:rowIndex, col:columnIndex});
            setIsEditing(true);
        }
    };

    const handleInputChange = (e, rowIndex, columnIndex) => {
        const updatedData = [...employeeData];
        updatedData[rowIndex][columnIndex] = e.target.value;

        document.cookie=`extractedValueEmployee=${encodeURIComponent(e.target.value)}; path=/restricted; max-age=3600; samesite=None; secure;`;
        setEmployeeData(updatedData);

        document.cookie=`extractedColumnEmployee=${columnIndex}; path=/restricted; max-age=3600; samesite=None; secure;`;
        
    }

    const handleInputKeyDown = (e) => {
        if (e.key === 'Enter') {
            setEditMode(null);
            //setIsEditing(false);
            document.getElementById("form-update-employee").submit();
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
            {sliceEmployeeData.map((employee, rowIndex) => (
                <tr
                key={rowIndex}
                className="text-center"
                >
                {employee.map((cell, columnIndex) => (
                    <td key={columnIndex}
                    onDoubleClick={() => handleDoubleClick(rowIndex, columnIndex)}
                    id={`${columnIndex}${rowIndex}`}
                    >
                      {editMode && editMode.row === rowIndex && editMode.col === columnIndex ?
                      (
                        <form
                        id="form-update-employee" 
                        action="/restricted/control-panel/update-employee" 
                        method="POST">
                            <input 
                            className="form-control"
                            type="text"
                            value={cell}
                            onChange={(e) => handleInputChange(e, rowIndex, columnIndex)}
                            onKeyDown={handleInputKeyDown}
                            onKeyUp={handleInputKeyUp}
                            />
                            <input type="hidden" name="employeeID" value={employee[0]} />
                        </form>
                      ) : (cell)
                      }
                    </td>
                ))}
                <td>
                    <form action="/restricted/control-panel/delete-employee" method="POST">
                      <input type="hidden" name="employeeID" value={employee[0]} />
                      <button className="delete btn btn-danger" type="submit">X</button>
                    </form>
                </td>
                </tr>
            ))}
            <tr className="text-center">
                <td>
                    <ButtonAddNewEmployee />
                </td>
            </tr>
            </tbody>
            </table>
        </>
    );
}