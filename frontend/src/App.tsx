import { Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Tasks from "./pages/Tasks";

export default function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/tasks" element={<Tasks />} />
      <Route path="*" element={<Navigate to="/tasks" />} />
    </Routes>
  );
}
