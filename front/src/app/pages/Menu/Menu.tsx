import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

const Menu: React.FC = () => {
    return (
        <>
            <Navbar className="bg-green navbar">
                <Container >
                <Navbar.Brand href="#inicio" className="color-white">Acervo Bibliotech</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link className="navbar-link color-white" href="#home">Início</Nav.Link>
                    <Nav.Link className="navbar-link color-white" href="#features">Acervo</Nav.Link>
                    <Nav.Link className="navbar-link color-white" href="#pricing">Alunos</Nav.Link>
                    <Nav.Link className="navbar-link color-white" href="#pricing">Relatórios</Nav.Link>
                </Nav>
                </Container>
            </Navbar>
        </>
    );
}

export default Menu;