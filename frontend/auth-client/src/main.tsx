import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Root from "./routes/Root.tsx";
import {StyledEngineProvider, ThemeProvider} from "@mui/material";
import theme from "./components/theme.ts";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root/>,
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
