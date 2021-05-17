import React, {Component} from "react";
import axios from "axios";
import {Link, withRouter} from "react-router-dom";
import Button from "@material-ui/core/Button";

class Cars extends Component{
    constructor(props) {
        super(props);
        this.state = {
            rows: []
        };
    }

    componentDidMount() {
        axios.get('http://localhost:8080/cars')
            .then((response) => {this.setState({rows: response.data});})
            .catch((error) => {console.log(error); this.setState({ message: error.message })});
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        this.componentDidMount();
    }

    render() {
        return (
            <main role="main" className="container">
                <div align="center">
                    {this.state.rows === null && <p>Loading menu...</p>}
                    <br/>
                    <table className="cars">
                        <thead>
                            <tr>
                                <th width={50}>Id</th>
                                <th width={100}>Number</th>
                                <th width={500}>Model</th>
                                <th width={200}>nameOfRentCompany</th>
                                <th width={200}>rented</th>
                            </tr>
                        </thead>
                        {this.state.rows && this.state.rows.map(car => (
                            <tbody align="center">
                                <tr>
                                    <td width={50}>{car.id}</td>
                                    <td width={100}>{car.number}</td>
                                    <td width={500}>{car.model}</td>
                                    <td width={200}>{car.rentCompany.name}</td>
                                    <td width={200}>{car.rented}</td>
                                    <Button component={Link} to={'/car/delete/' + car.id} variant="contained" color="primary">Delete</Button>
                                    <Button component={Link} to={'/car/update/' + car.id} variant="contained" color="primary">Update</Button>
                                </tr>
                            </tbody>
                        ))}
                    </table>
                    <div>
                        <Button component={Link} to="/car/add" variant="contained" color="primary">Add Car</Button>
                    </div>
                </div>
            </main>
        );
    }
}
export default withRouter(Cars);
