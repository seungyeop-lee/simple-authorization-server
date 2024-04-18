import {Outlet, useNavigate} from "react-router-dom";
import {useProcessState} from "./state/useProcessState.ts";
import Layout from "./routes/Layout.tsx";
import {useEffect} from "react";

function App() {
    const {setNavigate} = useProcessState((state) => ({
        setNavigate: state.setNavigate,
    }));
    let navigateFunction = useNavigate();

    useEffect(() => {
        setNavigate(navigateFunction);
    }, []);

    return <Layout><Outlet /></Layout>
}

export default App;
