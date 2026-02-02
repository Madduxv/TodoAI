async function loadTasks() {
  const res = await fetch("/task/get/all", { credentials: "include" });
  if (!res.ok) {
    document.getElementById("status").innerText = "Please log in again.";
    return;
  }

  const tasks = await res.json();
  const list = tasks.map(t => `<li>${t.description} (${t.currentStatus})</li>`).join("");
  document.getElementById("tasks").innerHTML = `<ul>${list}</ul>`;
}

async function addTask() {
  const description = document.getElementById("new-task").value;
  const res = await fetch("/task/new", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ description }),
    credentials: "include"
  });
  if (res.ok) {
    document.getElementById("new-task").value = "";
    loadTasks();
  }
}

async function deleteAll() {
  const res = await fetch("/task/delete/all", {
    method: "DELETE",
    credentials: "include"
  });
  if (res.ok) loadTasks();
}

loadTasks();
