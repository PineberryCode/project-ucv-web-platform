
const SupplierForm = () => {
    return (
        <div>
            <h4>Supplier</h4>
            <form className="p-5" action="/restricted/control-panel/process" method="POST">
                <div className="d-grid gap-3 p-3">
                    <div className="form-floating mb3">
                        <input name="description-name" type="text" className="form-control w-25" id="floating-input" placeholder="Nombre"/>
                        <label htmlFor="floating-input">Name</label>
                    </div>
                    <div className="form-floating mb-3">
                        <input name="contact-number" type="text" className="form-control w-25" id="floating-input" placeholder="Número de contacto" />
                        <label htmlFor="floating-input">Número de contacto</label>
                    </div>
                    <div className="form-floating mb-3">
                        <input name="email" type="email" className="form-control w-25" id="floating-input" placeholder="E-mail" />
                        <label htmlFor="floating-input">E-mail</label>
                    </div>
                    <div className="form-floating mb-3">
                        <input name="address" type="text" className="form-control w-25" id="floating-input" placeholder="Dirección/Ubicación" />
                        <label htmlFor="floating-input">Dirección/Ubicación</label>
                    </div>
                </div>
                <button type="submit" className="btn btn-primary p-4">d</button>
            </form>
        </div>
    );
}

//export default SupplierForm