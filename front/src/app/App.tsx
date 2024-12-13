import React from "react";
import Acervo from "./pages/Acervo/Acervo";
import 'bootstrap/dist/css/bootstrap.min.css';
import Menu from "./pages/Menu/Menu";

const App: React.FC = () => {
  return (
    <div className="App">
      <Menu />
      <Acervo />
    </div>
  );
};

export default App;
