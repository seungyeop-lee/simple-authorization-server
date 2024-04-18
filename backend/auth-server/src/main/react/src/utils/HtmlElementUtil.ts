export function createForm(action: string) {
    const form = document.createElement('form');
    form.style.display = 'none';
    form.action = action;
    form.method = 'POST';
    document.body.appendChild(form);
    return form;
}

export function createInput(name: string, value: string, to: HTMLFormElement) {
    let input = document.createElement('input');
    input.type = 'hidden';
    input.name = name;
    input.value = value;
    to.appendChild(input);
    return input;
}
