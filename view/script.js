const url = "http://localhost:8080/task/user/2";

// Função para esconder o loader após o carregamento dos dados
function hideLoader() {
    document.getElementById("loading").style.display = "none";
}

// Função para exibir as tarefas na tabela
function show(tasks) {
    let tab = `<thead>
        <th scope="col">#</th>
        <th scope="col">Description</th>
        <th scope="col">Username</th>
    </thead>`;

    for (let task of tasks) {  
        tab += `
            <tr>
                <td scope="row">${task.id}</td>
                <td scope="row">${task.description}</td>
                <td scope="row">${task.user.username}</td>
                <td scope="row">${task.user.id}</td>
            </tr>
        `;
    }

    document.getElementById("tasks").innerHTML = tab;
}

async function getApi(url) {
    try {
        const response = await fetch(url, { method: "GET", mode:"cors" });

        if (!response.ok) {
            console.error('Erro na requisição:', response.status);
            return;
        }

        const data = await response.json();
        console.log(data);  // Verifique o que está sendo retornado pela API
        hideLoader();
        show(data);  // Exibe os dados na tabela após recebê-los
    } catch (error) {
        console.error('Erro ao obter dados:', error);
        hideLoader();
        alert("Ocorreu um erro ao carregar os dados.");
    }
}

// Chamada da função getApi para buscar os dados
getApi(url);