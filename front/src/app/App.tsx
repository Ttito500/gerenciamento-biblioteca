import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { HashRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Acervo from "./pages/Acervo/Acervo";
import Emprestimos from "./pages/Emprestimos/Emprestimos";
import Alunos from "./pages/Alunos/Alunos";
import Secoes from "./pages/Secoes/Secoes";
import Menu from "./pages/Menu/Menu";
import Estantes from "./pages/Estantes/Estantes";
import Inicio from "./pages/Inicio/Inicio";
import Usuarios from "./pages/Usuarios/Usuarios";
import Login from "./pages/Autenticacao/Login";

const App: React.FC = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const handleLogin = (email: string, senha: string) => {
    if (email === "adelino@email.com" && senha === "cunha") {
      setIsAuthenticated(true);
    } else {
      alert("Credenciais incorretas");
    }
  };

  return (
      <Router>
        <div className="w-100">
          <Routes>
            <Route path="/" element={isAuthenticated ? <Navigate to="/inicio" /> : <Login onLogin={handleLogin} />} />
            <Route path="/inicio" element={isAuthenticated ? <><Menu /><Inicio /></> : <Navigate to="/" />} />
            <Route path="/acervo" element={isAuthenticated ? <><Menu /><Acervo /></> : <Navigate to="/" />} />
            <Route path="/emprestimos" element={isAuthenticated ? <><Menu /><Emprestimos /></> : <Navigate to="/" />} />
            <Route path="/alunos" element={isAuthenticated ? <><Menu /><Alunos /></> : <Navigate to="/" />} />
            <Route path="/secoes" element={isAuthenticated ? <><Menu /><Secoes /></> : <Navigate to="/" />} />
            <Route path="/estantes" element={isAuthenticated ? <><Menu /><Estantes /></> : <Navigate to="/" />} />
            <Route path="/usuarios" element={isAuthenticated ? <><Menu /><Usuarios /></> : <Navigate to="/" />} />
            <Route path="*" element={<Navigate to="/" />} />
          </Routes>
        </div>
      </Router>
  );
};

export default App;
