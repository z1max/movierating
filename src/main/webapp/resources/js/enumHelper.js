function addEnumItem(selectId, containerId){
    var select = document.getElementById(selectId);
    var value = select.options[select.selectedIndex].value;
    var container = document.getElementById(containerId);

    var div = document.createElement('div');

    var input = document.createElement('input');
    input.setAttribute('type', 'text');
    input.setAttribute('name', containerId);
    input.setAttribute('value', value);
    input.setAttribute('readonly', 'readonly');

    var button = document.createElement('button');
    button.setAttribute('onclick', 'remove(this)');
    button.innerHTML = 'Remove';

    div.appendChild(input);
    div.appendChild(button);

    container.appendChild(div);
}

function remove(element){
    element.closest('div').remove();
}