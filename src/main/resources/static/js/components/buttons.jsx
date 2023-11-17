
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
    const handleButtonClick = () => {setShowModal(true);}

    return (
        <>
            <button 
            type="button" 
            data-bs-toggle="modal" 
            data-bs-target="#staticBackdropProduct" 
            className="btn btn-success"
            onClick={handleButtonClick}>
            +
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