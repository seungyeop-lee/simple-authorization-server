import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {StyledEngineProvider, ThemeProvider} from "@mui/material";
import theme from "./components/theme.ts";
import Login from "./routes/Login.tsx";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Signup from "./routes/Signup.tsx";

const router = createBrowserRouter([
    {
        path: "/page/signin",
        element: <Login />,
    },
    {
        path: "/page/signup",
        element: <Signup />,
    }
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <StyledEngineProvider injectFirst>
            <ThemeProvider theme={theme}>
                <RouterProvider router={router} />
            </ThemeProvider>
        </StyledEngineProvider>
    </React.StrictMode>,
)
