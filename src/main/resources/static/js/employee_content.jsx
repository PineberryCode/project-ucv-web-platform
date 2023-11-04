
const EmployeeContent = () => {
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
        </>
    );
}