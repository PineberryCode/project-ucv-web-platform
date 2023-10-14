const { useState, useEffect } = React;

let tab_supplier = document.getElementById('tab-supplier');
let tab_sales = document.getElementById('tab-sales');
let tab_stock = document.getElementById('tab-stock');
let tab_users = document.getElementById('tab-users');

let overview_x = document.getElementById('overview-x');

// Utilize only one component
// Remember: Improve this code. Using of (useEffect and components).

const ComponentTabSupplier = () => {
    return (
        <div>
            <h1>Supplier</h1>
        </div>
    );
}

const ComponentTabSales = () => {
    return (
        <div>
            <h1>Sales</h1>
        </div>
    );
}

const ComponentTabStock = () => {
    return (
        <div>
            <h1>Using ChartJS</h1>
        </div>
    );
}

const ComponentTabUser = () => {
    return (
        <div>
            <h3>Administración de Usuarios</h3>
            <form className="p-5" action="/restricted/control-panel/config" method="POST">
                <div className="d-grid gap-3 p-3">
                    <div className="form-floating mb3">
                        <input name="username" type="text" className="form-control w-25" id="floating-input" placeholder="Username"/>
                        <label htmlFor="floating-input">Username</label>
                    </div>
                    <div className="form-floating mb-3">
                        <input name="password" type="password" className="form-control w-25" id="floating-input" placeholder="Password" />
                        <label htmlFor="floating-input">Password</label>
                    </div>
                    <select className="form-select mb-3 w-50" aria-label="Default select ROLE">
                        <option defaultValue="selected-role">Asignar role</option>
                        <option defaultValue="admin">ADMIN</option>
                        <option defaultValue="warehouse-manager">WAREHOUSE-MANAGER</option>
                        <option defaultValue="vendedor">VENDEDOR</option>
                    </select>
                </div>
                <button type="submit" className="btn btn-primary p-4">Crear usuario</button>
            </form>
        </div>
    );
}

const App = () => {
    const [tab, setTab] = useState ();

    const handleTabClick = (tab_component) => {
        setTab(tab_component);
    };

    useEffect(() => {
        tab_users.addEventListener('click', () => {
            const tab_compo= <ComponentTabUser />;
            handleTabClick(tab_compo);
        });


        tab_supplier.addEventListener('click', () => {
            const tab_compo = <ComponentTabSupplier />
            handleTabClick(tab_compo);
        });

        tab_sales.addEventListener('click', () => {
            const tab_compo = <ComponentTabSales />
            handleTabClick(tab_compo);
        })

        tab_stock.addEventListener('click', () => {
            const tab_compo = <ComponentTabStock />
            handleTabClick(tab_compo);
        })

        return () => {
            tab_users.removeEventListener('click', handleTabClick(<ComponentTabUser />));
            tab_supplier.removeEventListener('click', handleTabClick(<ComponentTabSupplier />));
            tab_sales.removeEventListener('click', handleTabClick(<ComponentTabSales />));
            tab_stock.removeEventListener('click', handleTabClick(<ComponentTabStock />));
        };
    }, []);
    return (
        <div className="container text-center p-5">
            {tab}
        </div>
    );
}

ReactDOM.render(<App />, overview_x);
/*function Rendering () {
    ReactDOM.render(<App />, overview_x);
}*/
