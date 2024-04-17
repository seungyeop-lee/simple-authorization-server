import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {StyledEngineProvider, ThemeProvider} from "@mui/material";
import theme from "./components/theme.ts";
import Login from "./routes/Login.tsx";

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <StyledEngineProvider injectFirst>
            <ThemeProvider theme={theme}>
                <Login />
            </ThemeProvider>
        </StyledEngineProvider>
    </React.StrictMode>,
)
