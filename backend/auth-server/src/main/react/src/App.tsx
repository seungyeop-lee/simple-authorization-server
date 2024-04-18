import {Outlet} from "react-router-dom";
import Layout from "./routes/Layout.tsx";

function App() {
    return <Layout><Outlet /></Layout>
}

export default App;
