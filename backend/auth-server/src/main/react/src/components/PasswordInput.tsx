import {IconButton, InputAdornment, OutlinedInput, OutlinedInputProps} from "@mui/material";
import React, {useState} from "react";
import {Visibility, VisibilityOff} from "@mui/icons-material";

function PasswordInput(props: OutlinedInputProps) {
    const [showPassword, setShowPassword] = useState<boolean>(false);
    const handleClickShowPassword = () => setShowPassword((show) => !show);
    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };

    return <OutlinedInput
        size="small"
        placeholder="비밀번호"
        fullWidth
        type={showPassword ? 'text' : 'password'}
        endAdornment={
            <InputAdornment position="end">
                <IconButton
                    aria-label="toggle password visibility"
                    onClick={handleClickShowPassword}
                    onMouseDown={handleMouseDownPassword}
                    edge="end"
                >
                    {showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>
            </InputAdornment>
        }
        {...props}
    />;
}

export default PasswordInput;
