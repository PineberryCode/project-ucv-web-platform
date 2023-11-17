
const MyToast = (
    id,
    classNameDiv,
    classNameToast,
    title,
    miniMessage,
    message
) => {
    return (
        <>
            <div 
            className={classNameDiv}
            style="z-index: 11">
                <div 
                id={id} 
                className="toast hide"
                role="alert" 
                aria-live="assertive" 
                aria-atomic="true">
                  <div 
                  className={classNameToast}>
                    <strong 
                    className="me-auto">
                    {title}
                    </strong>
                    <small>{miniMessage}</small>
                    <button type="button" className="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                  </div>
                  <div className="toast-body">
                    {message}
                  </div>
                </div>
            </div>
        </>
    );
}