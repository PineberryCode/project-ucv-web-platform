
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
            {showModal != true && <FormAddNewSupplier setShowModal={setShowModal} />}
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
            {showModal != true && <FormAddNewProduct setShowModal={setShowModal} />}
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
            {showModal != true && <FormAddNewEmployee setShowModal={setShowModal} />}
        </>
    );
}

const fetchAddProduct = () => {
    return new Promise((resolve, reject) => {
        const form = document.getElementById('form-sales-add-product');
        const formData = new FormData(form);

        fetch ('/restricted/control-panel/add-product', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.arrayBuffer();
        })
        .then(data => {
            let text = new TextDecoder('utf-8').decode(data);
            resolve(text = text.split(','));
        })
        .catch(error => {
            reject(error);
        })
    })
}

var arr_price_product;

const ButtonAddNewProductFromSale = () => {

    const [showToast, setToast] = useState(false);
    //var arr_price_product;
    const handleButtonClick = async (e) => {
        e.preventDefault();
        setToast(true);

        try {
            arr_price_product = await fetchAddProduct();
            for (let x of arr_price_product) {
                console.log(x);
            }
        } catch (error) {
            console.error('Exception: ', error);
        }
    }

    return (
        <>
        <button
        id="liveToastBtn"
        type="button"
        className="btn btn-outline-info"
        onClick={(e) => handleButtonClick(e)}>
        Agregar Producto
        </button>
        {showToast && <MyToast setToast={setToast}/>}
        </>
    );
}

const ButtonLookAtInvoice = () => {
    const [showModal, setShowModal] = useState(false);
    var array_total_product;
    if (arr_price_product != undefined) {
        console.log('ButtonLook',arr_price_product);
    }

    const handleButtonClick = (e) => {
        e.preventDefault();
        setShowModal(true);
    }

    return (
        <>
            <button
            type="button"
            data-bs-toggle="modal"
            data-bs-target="#staticBackdropInvoice"
            className="btn btn-info"
            onClick={(e) => handleButtonClick(e)}
            >
            Ver Factura
            </button>
            {showModal != true && 
            <PreviewInvoice 
            setShowModal={setShowModal}/>
            }
        </>
    );
}