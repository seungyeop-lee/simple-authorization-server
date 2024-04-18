import {create} from "zustand";
import {createForm, createInput} from "../utils/HtmlElementUtil.ts";

export enum Stage {
    Step1 = 'Step1',
    Step2 = 'Step2',
}

interface ProcessState {
    currentStage: Stage | undefined;
    inputEmail: string | undefined;
    setStage: (stage: Stage) => void;
    setInputEmail: (email?: string) => void;
    startSignin: (password: string) => void;
    startSignup: (password: string) => Promise<Response>;
}

export const useProcessState = create<ProcessState>((set, get) => ({
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
    },
    async startSignup(password) {
        return signup(get().inputEmail!, password);
    }
}));

function signin(email: string, password: string) {
    const form = createForm('/signin');
    createInput('email', email, form);
    createInput('password', password, form);
    form.submit();
}

async function signup(email: string, password: string) {
    return await fetch('/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({email: email, password: password})
    });
}
