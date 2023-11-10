
const Selection = ({
    ariaLabel,
    defaultLOption,
    numOption,
    valueL
}) => {
    const numOption = [];
    for (let i = 1; i <= numOption; i++) {
        options.push(<option key={i} value={i}></option>)
    }

    return (
        <>
            <select className="form-select" ariaLabel={ariaLabel}>
                <option selected>{defaultLOption}</option>
                {numOption}
            </select>
        </>
    );
}