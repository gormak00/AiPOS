import './App.css';
import React, {Component} from "react";
import CreateGame from "./create_components/CreateGame";
import CreateKey from "./create_components/CreateKey";
import CreateReview from "./create_components/CreateReview";
import CreateClient from "./create_components/CreateClient";
import DeleteReview from "./delete_components/DeleteReview";
import DeleteClient from "./delete_components/DeleteClient";
import DeleteKey from "./delete_components/DeleteKey";
import DeleteGame from "./delete_components/DeleteGame";
import UpdateGame from "./update_components/UpdateGame";
import UpdateKey from "./update_components/UpdateKey";
import UpdateReview from "./update_components/UpdateReview";
import UpdateClient from "./update_components/UpdateClient";
import {BrowserRouter, Link, Route} from "react-router-dom";
import MenuPopupState from "./MenuPopupState";
import Bookings from "./components/Bookings";
import Clients from "./components/Clients";
import RentCompanies from "./components/RentCompanies";
import Review from "./components/Review";
import Image from "./create_components/Image"
import ImageUser from "./create_components/ImageUser"
import {Button} from "@material-ui/core";
import Registration from "./components/Registration";
import Login from "./components/Login";


class App extends Component{
    render() {
        return (
            <BrowserRouter>
                <header className="topnav">
                    <div>
                        <MenuPopupState/>
                    </div>
                    <div>
                        <Button component={Link} to="/Registration" style={{color: 'white'}}>SignUp</Button>
                    </div>
                    <div>
                        <Button component={Link} to="/Login" style={{color: 'white'}}>Login</Button>
                    </div>
                </header>
                <div className="App">
                    <Route exact path='/Login' component={Login}/>
                    <Route exact path='/Registration' component={Registration}/>
                    <Route exact path='/clients' component={Clients}/>
                    <Route exact path='/client/add' component={CreateClient}/>
                    <Route exact path='/client/delete/:id' component={DeleteClient}/>
                    <Route exact path='/client/update/:id' component={UpdateClient}/>

                    <Route exact path='/bookings' component={Bookings}/>
                    <Route exact path='/booking/add' component={CreateBooking}/>
                    <Route exact path='/booking/delete/:id' component={DeleteBooking}/>
                    <Route exact path='/booking/update/:id' component={UpdateBooking}/>

                    <Route exact path='/rentCompanies' component={RentCompanies}/>
                    <Route exact path='/rentCompany/add' component={CreateRentCompany}/>
                    <Route exact path='/rentCompany/delete/:id' component={DeleteRentCompany}/>
                    <Route exact path='/rentCompany/update/:id' component={UpdateRentCompany}/>

                    <Route exact path='/Review' component={Review}/>
                    <Route exact path='/CreateReview' component={CreateReview}/>
                    <Route exact path='/reviews/delete/:id' component={DeleteReview}/>
                    <Route exact path='/reviews/update/:id' component={UpdateReview}/>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
