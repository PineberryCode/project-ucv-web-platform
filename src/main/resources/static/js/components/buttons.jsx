
const buttonLogout = () => {
    return (
        <div class="position-absolute top-0 end-0 shadow-lg p-3 mb-5 bg-body rounded">
            <form action="/restricted/control-panel/logout" method="POST">
                <button type="submit" class="btn btn-danger">Log Out</button>
            </form>
        </div>
    );
}