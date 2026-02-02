import { useEffect, useState } from "react";
import { apiFetch } from "../api";
import type { Task } from "../types";

export default function Tasks() {
  const [tasks, setTasks] = useState<Task[]>([]);

  useEffect(() => {
    apiFetch<Task[]>("/task/get/all").then(setTasks);
  }, []);

  return (
    <div className="page">
      <h1>Your Tasks</h1>

      <ul>
        {tasks.map(task => (
          <li key={task.id}>
            {task.title}
          </li>
        ))}
      </ul>
    </div>
  );
}
