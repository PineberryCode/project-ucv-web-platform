
/*
classNameDiv,
  title,
  miniMessage,
  message,
  setToast
*/
const MyToast = ({
  setToast
}
) => {

  const closeToast = () => {
    setToast(false);
  }
  console.log('false')

  useEffect(() => {
    document.getElementById('liveToast').classList.add('show');

    const timeOut = setTimeout(() => {
      document.getElementById('liveToast').classList.remove('show');
      setToast(false);
    }, 2000);

    return () => clearTimeout(timeOut);

  }, []);

    return (
        <>
            <div 
            className="position-fixed bottom-0 end-0 p-3"
            style={{zIndex: 11}}>
                <div 
                id="liveToast" 
                className="toast bg-success"
                role="alert" 
                aria-live="assertive" 
                aria-atomic="true">
                  <div 
                  className="toast-header bg-primary">
                    <strong 
                    className="me-auto"
                    style={{color: '#fff'}}>
                    Importante!
                    </strong>
                    <small>1 sec ago</small>
                    <button 
                    type="button" 
                    className="btn-close" 
                    data-bs-dismiss="toast" 
                    aria-label="Close"  
                    onClick={closeToast}>
                    </button>
                  </div>
                  <div 
                  className="toast-body"
                  style={{
                    color: '#000', 
                    fontWeight: 'bold'
                    }}>
                    Producto Registrado
                  </div>
                </div>
            </div>
        </>
    );
}