document.addEventListener('DOMContentLoaded', handleEnumContainers);
var genresContainer = document.getElementById('genres');
genresContainer.addEventListener('DOMNodeInserted', function (event) {
    handleEnumElement(genresContainer);
});
var countriesContainer = document.getElementById('countries');
countriesContainer.addEventListener('DOMNodeInserted', function (event) {
    handleEnumElement(countriesContainer);
});


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
    input.style.width = '70%';

    var button = document.createElement('button');
    button.classList.add('button');
    button.style.padding = "11px 32px";
    button.setAttribute('onclick', 'remove(this)');
    button.innerHTML = 'Remove';

    div.appendChild(input);
    div.appendChild(button);

    container.appendChild(div);
}

function remove(element){
    element.closest('div').remove();
}

function handleEnumContainers(){
    var genres = document.getElementById('genres');
    var countries = document.getElementById('countries');
    handleEnumElement(genres);
    handleEnumElement(countries);
}

function handleEnumElement(element){
    if (element.children.length === 0){
        element.style.display = 'none';
    } else {
        element.style.display = 'block';
    }

}