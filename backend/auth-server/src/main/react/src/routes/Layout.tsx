import React from "react";

function Layout(props: { children: React.ReactNode }) {

    return (
        <div className="h-svh">
            <div className="flex h-full">
                <div className="hidden basis-1/2 p-10 flex-col justify-between bg-[#18181b] text-white lg:flex">
                    <div className="text-2xl">Simple Auth Service</div>
                    <blockquote className="space-y-2">
                        <p className="text-lg">
                            “본 인증/인가 시스템은 테스트용입니다.”
                        </p>
                        <footer className="text-sm">
                            <a href="https://github.com/seungyeop-lee/simple-authorization-server" target="_blank">Source Code</a>
                        </footer>
                    </blockquote>
                </div>
                <div className="grow flex flex-col justify-center items-center lg:basis-1/2">
                    <div className="w-2/5 min-w-96 max-w-screen-sm">
                        {props.children}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Layout;
