
const ButtonAddNew = () => {

    const [showModal, setShowModal] = useState(false);

    const handleButtonClick = () => {
        setShowModal(true);
    }

    return (
        <>
            <button 
            type="button" 
            data-bs-toggle="modal" 
            data-bs-target="#staticBackdrop" 
            className="btn btn-success"
            onClick={handleButtonClick}>
            +
            </button>
            {showModal && <FormAddNew setShowModal={setShowModal} />}
        </>
    );
}