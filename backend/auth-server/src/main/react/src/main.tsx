import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {StyledEngineProvider, ThemeProvider} from "@mui/material";
import theme from "./components/theme.ts";
import Signin from "./routes/Signin.tsx";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Signup from "./routes/Signup.tsx";
import App from "./App.tsx";

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <StyledEngineProvider injectFirst>
            <ThemeProvider theme={theme}>
                <BrowserRouter>
                    <Routes>
                        <Route path='/page' element={<App />}>
                            <Route path='signin' element={<Signin />} />
                            <Route path='signup' element={<Signup />} />
                        </Route>
                    </Routes>
                </BrowserRouter>
            </ThemeProvider>
        </StyledEngineProvider>
    </React.StrictMode>,
)
