
//const {useState} = React;

const ButtonAddNewSupplier = () => {

    const [showModal, setShowModal] = useState(false);
    const handleButtonClick = () => {setShowModal(true);}

    return (
        <>
            <button 
            type="button" 
            data-bs-toggle="modal" 
            data-bs-target="#staticBackdropSupplier" 
            className="btn btn-success"
            onClick={handleButtonClick}>
            +
            </button>
            {showModal && <FormAddNewSupplier setShowModal={setShowModal} />}
        </>
    );
}

const ButtonAddNewProduct = () => {
    const [showModal, setShowModal] = useState(false);
    const handleButtonClick = () => {
        setShowModal(true);
    }

    return (
        <>
            <button 
            type="button" 
            data-bs-toggle="modal" 
            data-bs-target="#staticBackdropProduct" 
            className="btn btn-success"
            onClick={handleButtonClick}>
            Agregar Nuevo Producto
            </button>
            {showModal && <FormAddNewProduct setShowModal={setShowModal} />}
        </>
    );
}

const ButtonAddNewEmployee = () => {

    const [showModal, setShowModal] = useState(false);
    const handleButtonClick = () => {setShowModal(true);}
    
    return (
        <>
            <button
            type="button"
            data-bs-toggle="modal"
            data-bs-target="#staticBackdropEmployee"
            className="btn btn-success"
            onClick={handleButtonClick}
            >
            +
            </button>
            {showModal && <FormAddNewEmployee setShowModal={setShowModal} />}
        </>
    );
}

const ButtonAddNewProductFromSale = () => {

    const [showToast, setToast] = useState(false);
    const handleButtonClick = (e) => {
        e.preventDefault();
        setToast(true);
        /*setTimeout(() => {
            document.getElementById('form-sales-add-product').submit();
        },1000);*/
        /*
         <form 
                    id="form-sales-add-product" 
                    action="/restricted/control-panel/add-product" 
                    method="POST">
        */
       const form = document.getElementById('form-sales-add-product');
       const formData = new FormData(form);
        fetch ('/restricted/control-panel/add-product', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            console.log('Solicitud completada.',response);
            setTimeout (() => {
                
            }, 1000);
        })
        .catch(error => {
            console.error('Exception: ',error)
        });
    }

    return (
        <>
        <button
        id="liveToastBtn"
        type="button"
        className="btn btn-outline-info"
        onClick={handleButtonClick}>
        Agregar Producto
        </button>
        {showToast && <MyToast setToast={setToast}/>}
        </>
    );
}

const ButtonLookAtInvoice = () => {
    const [showModal, setShowModal] = useState(false);
    const handleButtonClick = () => {setShowModal(true);}

    return (
        <>
            <button
            type="button"
            data-bs-toggle="modal"
            data-bs-target="#staticBackdropInvoice"
            className="btn btn-info"
            onClick={handleButtonClick}
            >
            Ver Factura
            </button>
            {showModal && <PreviewInvoice setShowModal={setShowModal} />}
        </>
    );
}