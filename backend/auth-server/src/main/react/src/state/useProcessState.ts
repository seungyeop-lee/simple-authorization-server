import {create} from "zustand";
import {NavigateFunction} from "react-router/dist/lib/hooks";
import {createForm, createInput} from "../utils/HtmlElementUtil.ts";

export enum Stage {
    Step1 = 'Step1',
    Step2 = 'Step2',
}

interface ProcessState {
    navigate: NavigateFunction | undefined;
    setNavigate: (navigate: NavigateFunction) => void;
    currentStage: Stage | undefined;
    inputEmail: string | undefined;
    setStage: (stage: Stage) => void;
    setInputEmail: (email?: string) => void;
    startSignin: (password: string) => void;
}

export const useProcessState = create<ProcessState>((set, get) => ({
    navigate: undefined,
    setNavigate(navigate) {
        set({navigate: navigate});
    },
    currentStage: undefined,
    inputEmail: undefined,
    setStage(stage) {
        set({currentStage: stage})
    },
    setInputEmail(email?) {
        set({inputEmail: email})
    },
    async startSignin(password) {
        signin(get().inputEmail!, password);
    }
}));

function signin(email: string, password: string) {
    const form = createForm('/signin');
    createInput('username', email, form);
    createInput('password', password, form);
    form.submit();
}
