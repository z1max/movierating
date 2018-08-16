function edit(form) {
    var name = form.elements["name"].getAttribute("value");
    var id = form.elements["id"].getAttribute("value");

    var addEditForm = document.getElementById("addEditForm");

    var idInput = addEditForm.elements["id"];
    if (typeof idInput !== 'undefined') {
        idInput.setAttribute("value", id);
    } else {
        var input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "id");
        input.setAttribute("value", id);
        addEditForm.appendChild(input);
    }

    addEditForm.elements["name"].setAttribute("value", name);

    addEditForm.elements["submit"].setAttribute("value", "Update");

    addEditForm.setAttribute("action", "front?command=EditGenreOrCountry");

    return false;
}