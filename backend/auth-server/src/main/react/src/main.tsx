import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {StyledEngineProvider, ThemeProvider} from "@mui/material";
import theme from "./components/theme.ts";
import Signin from "./routes/Signin.tsx";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Signup from "./routes/Signup.tsx";
import Layout from "./routes/Layout.tsx";
import Terms from "./routes/Terms.tsx";
import Privacy from "./routes/Privacy.tsx";

const router = createBrowserRouter([
    {
        path: "/page/signin",
        element: <Layout><Signin/></Layout>,
    },
    {
        path: "/page/signup",
        element: <Layout><Signup/></Layout>,
    },
    {
        path: "/page/terms",
        element: <Terms/>,
    },
    {
        path: "/page/privacy",
        element: <Privacy/>,
    },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <StyledEngineProvider injectFirst>
            <ThemeProvider theme={theme}>
                <RouterProvider router={router}/>
            </ThemeProvider>
        </StyledEngineProvider>
    </React.StrictMode>,
)
