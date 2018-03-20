

// function createNewDiv(taskCodeId) {
//     let dom = `
//         <div class="form-group row">
//             <label for="${taskCodeId}" class="col-2 col-form-label">Task code</label>
//             <div class="col-10">
//                 <input type="text" class="form-control" id="${taskCodeId}" >
//             </div>
//         </div>
//         `;
//     return dom;
// }

function createDivElement(divClass) {
    let div = document.createElement("DIV");
    div.className = divClass;
    return div;
}

function createLabelElement(labelClass, text){
    let label = document.createElement("LABEL");
    label.className = labelClass;
    label.textContent = text;
    return label;
}

function createInputElement(inputClass, type, id){
    let input = document.createElement("INPUT");
    input.className = inputClass;
    input.id = id;
    input.type = type;
    return input;
}

function createButtonElement(buttonClass, text, onclick){
    let button = document.createElement("BUTTON");
    button.className = buttonClass;
    button.textContent = text;
    button.onclick = onclick;
    return button;
}

function onRemoveButtonClick(){
    let div = document.getElementById('task');
    div.removeChild(div.lastChild);
}

function taoFunction() {
    let divWrapper = createDivElement("form-group row");
    let divInner = createDivElement("col-10");
    let labelInner = createLabelElement("col-2 col-form-label", "sometext");
    let input = createInputElement("form-control form-control-sm", "button", "username");

    divInner.appendChild(input);
    divWrapper.appendChild(labelInner);
    divWrapper.appendChild(divInner);
    divWrapper.appendChild(createButtonElement("btn btn-default", "Remove", onclick))
    document.getElementById('task').appendChild(divWrapper);
}

