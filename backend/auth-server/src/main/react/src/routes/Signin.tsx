import {Button, OutlinedInput} from "@mui/material";
import {Link} from "react-router-dom";
import GoogleIcon from "@mui/icons-material/Google";
import AppleIcon from "@mui/icons-material/Apple";
import {Stage, useProcessState} from "../state/useProcessState.ts";
import {useEffect, useRef} from "react";
import PasswordInput from "../components/PasswordInput.tsx";

function Signin() {
    const {setStage, setInputEmail, currentStage} = useProcessState();
    useEffect(() => {
        setStage(Stage.Step1);
        setInputEmail(undefined);
    }, []);

    return currentStage === Stage.Step1 ? <Step1/> : <Step2/>;
}

function Step1() {
    const {setStage, setInputEmail} = useProcessState();
    const emailInputRef = useRef<HTMLInputElement>();

    return <>
        <div className="text-center">
            <h1 className="text-2xl font-semibold tracking-tight">다시 오신 것을 환영합니다</h1>
        </div>
        <div className="grid gap-6 mt-7">
            <div>
                <OutlinedInput size="small" placeholder="이메일 주소" fullWidth autoComplete="email" inputRef={emailInputRef}/>
                <Button fullWidth size="large" className="mt-3 text-sm text-white bg-black hover:bg-slate-900" onClick={() => {
                    let email = emailInputRef.current?.value;
                    if (!email) {
                        alert('이메일 주소를 입력해주세요');
                        return;
                    }
                    setInputEmail(emailInputRef.current?.value);
                    setStage(Stage.Step2);
                }}>계속하기</Button>
                <p className="text-center mt-3 text-sm text-slate-400">계정이 없으신가요? <Link to="/page/signup" className="text-black">가입하기</Link></p>
            </div>
            <div className="relative">
                <div className="absolute inset-0 flex items-center"><span className="w-full border-t"></span></div>
                <div className="relative flex justify-center text-xs uppercase">
                    <span className="bg-white px-2 text-slate-400">또는</span>
                </div>
            </div>
            <div className="grid gap-2">
                <Button fullWidth size="medium" variant="outlined" color="inherit" className="text-sm text-black bg-white hover:bg-slate-50"
                        onClick={() => window.location.href = '/oauth2/authorization/google-idp'}>
                    <GoogleIcon className="w-5 h-5 mr-2"/>Google 계정으로 계속하기
                </Button>
                <Button fullWidth size="medium" variant="outlined" color="inherit" className="text-sm text-black bg-white hover:bg-slate-50" onClick={() => alert('준비 중 입니다.')}>
                    <AppleIcon className="w-5 h-5 mr-2"/>Apple 계정으로 계속하기
                </Button>
            </div>
        </div>
        <p className="mt-5 px-8 text-center text-sm text-slate-400">
            <Link className="underline underline-offset-4 hover:text-primary" to="/page/terms">이용 약관</Link>
            <span className="mx-2">|</span>
            <Link className="underline underline-offset-4 hover:text-primary" to="/page/privacy">개인정보 처리방침</Link>
        </p>
    </>;
}

function Step2() {
    const {inputEmail, startSignin} = useProcessState();
    const passwordInputRef = useRef<HTMLInputElement>();

    return <>
        <div className="text-center">
            <h1 className="text-2xl font-semibold tracking-tight">비밀번호를 입력해주세요</h1>
        </div>
        <div className="grid gap-6 mt-7">
            <div>
                <OutlinedInput size="small" placeholder="이메일 주소" fullWidth autoComplete="email" value={inputEmail} disabled/>
                <PasswordInput className="mt-3" inputRef={passwordInputRef}/>
                <Button fullWidth size="large" className="mt-3 text-sm text-white bg-black hover:bg-slate-900" onClick={() => {
                    let password = passwordInputRef.current?.value;
                    if (!password) {
                        alert('비밀번호를 입력해주세요');
                        return;
                    }
                    startSignin(password);
                }}>계속</Button>
                <p className="text-center mt-3 text-sm text-slate-400">계정이 없으신가요? <Link to="/page/signup" className="text-black">가입하기</Link></p>
            </div>
        </div>
        <p className="mt-5 px-8 text-center text-sm text-slate-400">
            <Link className="underline underline-offset-4 hover:text-primary" to="/page/terms">이용 약관</Link>
            <span className="mx-2">|</span>
            <Link className="underline underline-offset-4 hover:text-primary" to="/page/privacy">개인정보 처리방침</Link>
        </p>
    </>;
}

export default Signin
