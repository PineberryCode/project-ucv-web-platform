
const Input = ({
    name,
    type,
    className,
    defaultValue,
    placeholder,
    min,
    minLength,
    max,
    maxLength,
}) => {
    return (
        <div className="form-floating mb-3">
            <input 
            name={name} 
            type={type} 
            className={className} 
            id="floatingInput" 
            defaultValue={defaultValue !== undefined ? defaultValue : ''} 
            placeholder={placeholder}
            min={min !== undefined ? min : ''}
            minLength={minLength !== undefined ? minLength : ''}
            max={max !== undefined ? max : ''}
            maxLength={maxLength !== undefined ? maxLength : ''}
            />
            <label htmlFor="floatingInput">{placeholder}</label>
        </div>
    );
}