
const ModalAddNew = ({setShowModal}) => {
    console.log('Renderizando');
    const closeModal = () => {
        setShowModal(false);
    }

    return (
        <div className="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="staticBackdropLabel">Nuevo Proveedor</h5>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="/restricted/control-panel/register-supplier" method="POST">
                <div className="modal-body">
                  <Input name="name" type="text" className="form-control" placeholder="Nombre" />
                  <Input name="cel" type="text" className="form-control" placeholder="Número" />
                  <Input name="email" type="email" className="form-control" placeholder="E-mail" />
                  <Input name="address" type="text" className="form-control" placeholder="Dirección/Localidad" />
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={closeModal}>Close</button>
                  <button type="submit" className="btn btn-primary">Registrar</button>
                </div>
            </form>
          </div>
        </div>
        </div>
    );
}