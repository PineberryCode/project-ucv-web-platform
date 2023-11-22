
const {useState, useEffect} = React;

const SalesContent = () => {

    const [category, setCategory] = useState('');
    const [arrProduct, setArrProduct] = useState([]);

    function handleSelectChange (e) {
        e.preventDefault();
        setCategory(e.target.value,);
        document.cookie=`selectedCategory=${e.target.value}; path=/restricted; max-age=3600; samesite=None; secure;`;
        
        fetch ('/restricted/control-panel/extract-category', {
            method: 'POST'
        })
        .then(response => {
            console.log('Solicitud completada.',response);
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
        })
        .catch(error => {
            console.error('Exception: ',error)
        });

    }

    useEffect(() => {
        console.log("Selected: ", category)
    }, [category]);

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
                    <form id="form-sales-add-product">
                    <select
                    name="category"
                    className="form-select form-select-sm mb-3 w-75" 
                    aria-label=".form-select-sm category"
                    onChange={handleSelectChange}
                    >
                        <option defaultValue>Seleccione la categoría</option>
                        <option value="Porcelanato">Porcelanato</option>
                        <option value="Inodoro">Inodoro</option>
                        <option value="Lavadero">Lavadero</option>
                        <option value="Accesorios">Accesorios</option>
                    </select>
                    <select
                    name="product-name"
                    className="form-select form-select-sm mb-3" 
                    aria-label=".form-select-sm category"
                    >
                        <option defaultValue>Seleccione el producto</option>
                        {arrProduct.map((product, index) => (
                            <option 
                            key={index} 
                            value={product}
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
                    </form>
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