
const {useState, useEffect} = React;

const SalesContent = () => {

    const [category, setCategory] = useState('');
    const [arrProduct, setArrProduct] = useState([]);

    function handleSelectChange (e) {
        setCategory(e.target.value,);
        document.cookie=`selectedCategory=${e.target.value}; path=/restricted; max-age=3600; samesite=None; secure;`;
        
        fetch ('/restricted/control-panel/extract-category', {
            method: 'POST'
        })
        .then(response => {
            console.log('Solicitud completada.',response)
        })
        .catch(error => {
            console.error('Exception: ',error)
        });

        setTimeout (() => {
            const product_str = getCookie("ProductsString"); //render.jsx

        var first_time = product_str.includes('%20') 
        ? product_str.replaceAll('%20',' ')
        : product_str;
        var second_time = first_time.includes('%c')
        ? first_time.replaceAll('%c',',')
        : first_time;

        console.log(second_time);

        //arrProduct = second_time.split(',');
        setArrProduct(second_time.split(','));

        console.log(arrProduct)
        }, 1000);

    }

    useEffect(() => {
        console.log("Selected: ", category)
    }, [category]);

    function handleButtonClick () {
        console.log("Selected: ",category);
    }

    return (
        <>
        <div className="row mt-5 text-center justify-content-center">
            <div className="col-md-5 m-4">
            <div
            className="card border-0"
            >
                <div 
                className="card-body shadow-lg p-3 mb-5 bg-body rounded"
                >
                    <h5
                    className="p-3"
                    >
                        Datos del Producto
                    </h5>
                    <select
                    name="categories"
                    className="form-select form-select-sm mb-3 w-75" 
                    aria-label=".form-select-sm categories"
                    onChange={handleSelectChange}
                    >
                        <option defaultValue>Seleccione la categoría</option>
                        <option defaultValue="Porcelanato">Porcelanato</option>
                        <option defaultValue="Inodoro">Inodoro</option>
                        <option defaultValue="Lavadero">Lavadero</option>
                        <option defaultValue="Accesorios">Accesorios</option>
                    </select>
                    <select
                    name="product-name"
                    className="form-select form-select-sm mb-3" 
                    aria-label=".form-select-sm categories"
                    >
                        <option defaultValue>Seleccione el producto</option>
                        {arrProduct.map((product, index) => (
                            <option 
                            key={index} 
                            defaultValue={product}
                            >
                            {product}
                            </option>
                        ))}
                    </select>
                    <Input 
                    name="quantity" 
                    type="number"
                    className="form-control"
                    defaultValue="0"
                    placeholder="Cantidad"
                    min="0"
                    max="1000"
                    />
                    <ButtonAddNewProductFromSale />
                </div>
            </div>
        </div>
            <div className="col-md-5 m-4">
                <div
                className="card border-0"
                >
                    <div 
                    className="card-body shadow-lg p-3 mb-5 bg-body rounded"
                    >
                        <h5
                        className="p-3"
                        >
                            Datos del Cliente
                        </h5>
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
                    </div>
                </div>
            </div>
        </div>
        <div
        className="text-center"
        >
            <ButtonLookAtInvoice />
        </div>
        </>
    );
}