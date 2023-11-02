
const Input = ({
    name,
    type,
    className,
    placeholder
}) => {
    return (
        <div className="form-floating mb-3">
            <input name={name} type={type} className={className} id="floatingInput" placeholder={placeholder} />
            <label htmlFor="floatingInput">{placeholder}</label>
        </div>
    );
}