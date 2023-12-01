
const FormAddNewSupplier = ({setShowModal}) => {
  
  const closeModal = () => {setShowModal(false);}

  return (
    <div className="modal fade" id="staticBackdropSupplier" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
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
                <Input name="email" type="text" className="form-control" placeholder="E-mail" />
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

const FormAddNewProduct = ({setShowModal}) => {
  
  const closeModal = () => {setShowModal(false);}

  return (
    <div className="modal fade" id="staticBackdropProduct" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="staticBackdropLabel">Nuevo Producto</h5>
            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form action="/restricted/control-panel/register-product" method="POST">
              <div className="modal-body">
                <select name="category" className="form-select mb-3" aria-label="Default Select Value">
                  <option selected>Selecciona una categoría</option>
                  <option value="1">Porcelanato</option>
                  <option value="2">Inodoro</option>
                  <option value="3">Lavadero</option>
                  <option value="4">Accesorios</option>
                </select>
                <Input name="name" type="text" className="form-control" placeholder="Nombre Producto" />
                <Input 
                name="quantity" 
                type="number" 
                className="form-control" 
                placeholder="Cantidad" 
                defaultValue="0"
                min="0"
                max="1000"
                />
                <Input 
                name="unit-price" 
                type="number" 
                className="form-control" 
                placeholder="Precio Unitario" 
                defaultValue="0"
                min="0"
                step="0.5"
                />
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
// form className="needs-validation"
const FormAddNewEmployee = ({setShowModal}) => {
  
  const closeModal = () => {setShowModal(false);}

  return (
    <div
    className="modal fade" 
    id="staticBackdropEmployee" 
    data-bs-backdrop="static" 
    data-bs-keyboard="false" 
    tabIndex="-1" 
    aria-labelledby="staticBackdropLabel" 
    aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="staticBackdropLabel">Nuevo Empleado</h5>
            <button 
            type="button" 
            className="btn-close" 
            data-bs-dismiss="modal" 
            aria-label="Close">
            </button>
          </div>
          <form 
          action="/restricted/control-panel/register-employee" 
          method="POST">
              <div className="modal-body">
                <div 
                className="accordion shadow p-2 mb-4 bg-body rounded" 
                id="accordionUser"
                >
                <div className="accordion-item">
                  <h2 className="accordion-header" id="headingOne">
                    <button 
                    className="accordion-button" 
                    type="button" 
                    data-bs-toggle="collapse" 
                    data-bs-target="#collapseOne" 
                    aria-expanded="true" 
                    aria-controls="collapseOne">
                      Usuario
                    </button>
                  </h2>
                  <div 
                  id="collapseOne" 
                  className="accordion-collapse collapse" 
                  aria-labelledby="headingOne" 
                  data-bs-parent="#accordionUser">
                    <div className="accordion-body">
                      <select name="roles" className="form-select mb-3" aria-label="Default Select Value">
                        <option defaultValue>Selecciona el Rol</option>
                        <option defaultValue="ADMIN">ADMIN</option>
                        <option defaultValue="WAREHOUSE_MANAGE">Encargado de Almacén</option>
                        <option defaultValue="VENDEDOR">Área de Ventas</option>
                      </select>
                      <Input name="username" type="text" className="form-control" placeholder="Username" />
                      <Input name="password" type="password" className="form-control" placeholder="Password" />
                    </div>
                  </div>
                </div>
                </div>
                <Input name="email" type="text" className="form-control" placeholder="E-mail" />
                <Input name="name" type="text" className="form-control" placeholder="Nombres" />
                <Input name="lastname" type="text" className="form-control" placeholder="Apellidos" />
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

const PreviewInvoice = ({setShowModal}) => {

  //console.log('Preview: ',array);

  function closeModal () {setShowModal(false);}

  var getProductsAdded = getCookie("CookieSaleDetails");
  console.log(getProductsAdded);
  
  let firstTime = getProductsAdded.includes("%0")
  ? getProductsAdded.replaceAll("%0","-")
  : getProductsAdded;
  let secondTime = firstTime.includes("%80")
  ? firstTime.replaceAll("%80",",")
  : firstTime;
  let thirdTime = secondTime.includes("%25")
  ? secondTime.replaceAll("%25"," ")
  : secondTime;

  var pairs = thirdTime.split(",");
  var arrayProductsAdded = [['Name Product','Quantity']];

  for (let h of pairs) {
    let name = h.split("-")[0];
    let quantity = h.split("-")[1];

    arrayProductsAdded.push([name,quantity]);
  }

  var sliceArrayProductsAdded = arrayProductsAdded.slice(1,arrayProductsAdded.length);

  const handleRemoveSpecificProduct = (e, index) => {
    e.preventDefault();

    const form = document.getElementById('form-delete-one-product');
    const formData = new FormData(form);

    fetch ("/restricted/control-panel/delete-only-one-product", {
      method: 'POST',
      body: formData
    }).then(response => {
      console.log(response);
      //console.log([...formData]);
      setTimeout(() => {
        document.getElementById(`textarea-${index}`).remove();
        document.getElementById(`btn-${index}`).remove();
      }, 500);
    }).catch(e => {
      console.log(e)
    })
  }

  const handleClientDataAndGeneral = (e) => {
    e.preventDefault();
    
    const form = document.getElementById('form-client-data');
    const formData = new FormData(form);

    fetch ("/restricted/control-panel/register-sale", {
      method: 'POST',
      body: formData
    }).then(response => {
      console.log(response);
      //Another method that shows details
    }).catch(e => {
      console.log(e);
    });
  }

  return (
    <div
    className="modal fade" 
    id="staticBackdropInvoice" 
    data-bs-backdrop="static" 
    data-bs-keyboard="false" 
    tabIndex="-1" 
    aria-labelledby="staticBackdropLabel" 
    aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="staticBackdropLabel">Preview de la Factura</h5>
            <button 
            type="button" 
            className="btn-close" 
            data-bs-dismiss="modal" 
            aria-label="Close">
            </button>
          </div>
          <div className="modal-body">
              <h5
              className="p-3"
              >
                  Datos del Cliente
              </h5>
              <form
              id="form-client-data">
              <Input 
              name="surname" 
              type="text"  
              className="form-control"
              placeholder="Nombre completo"
              />
              <Input 
              name="cel-client"
              type="text"
              className="form-control"
              placeholder="Número de Contacto/Teléfono"
              />
              </form>
              {sliceArrayProductsAdded.map((product_added, index) => (
                <form 
                key={index} 
                id="form-delete-one-product">
                <div
                className="input-group mb-3">
                  <textarea 
                  key={index}
                  id={`textarea-${index}`}
                  className="form-control"
                  type="text"
                  value={`Producto 0${index+1}: ${product_added[0]}\nCantidad: ${product_added[1]}`}
                  style={
                    {maxHeight: '80px',
                    minHeight: '80px'
                    }
                  }
                  readOnly
                  disabled />
                  <input type="hidden" name="key-product" value={product_added[0]} />
                  <button
                  id={`btn-${index}`}
                  type="button"
                  className="btn btn-outline-danger"
                  onClick={(e) => handleRemoveSpecificProduct(e,index)}>
                  X
                  </button>
                </div>
                </form>
              ))}
              
            </div>
          <div className="modal-footer">
            <button 
            type="button" 
            className="btn btn-secondary" 
            data-bs-dismiss="modal" 
            onClick={closeModal}>
            Close
            </button>
            <button 
            type="button" 
            className="btn btn-primary"
            onClick={(e) => handleClientDataAndGeneral(e)}>
            Registrar
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}