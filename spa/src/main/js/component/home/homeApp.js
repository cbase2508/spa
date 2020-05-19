import React from 'react';
import ReactDOM from 'react-dom';

import {Pagination,
    Alert,
    Button,
    Col,
    Row
} from 'react-bootstrap';
const rest = require('rest');
const phonenoRegex = /^\(?([0-9]{0,3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
export default class HomeApp extends React.Component {

        constructor(props) {
            super(props);
            this.state = {
                list: [],
                 pageInfo: { totalElements: 0, currentPage: 0, totalPages: 0}
            };

            this.onPhonoChange = this.onPhonoChange.bind(this);
            this.handleFind = this.handleFind.bind(this);
            this.handlePageNation = this.handlePageNation.bind(this);
            this.handleNavFirst = this.handleNavFirst.bind(this);
            this.handleNavPrev = this.handleNavPrev.bind(this);
            this.handleNavNext = this.handleNavNext.bind(this);
            this.handleNavLast = this.handleNavLast.bind(this);
        }

        onPhonoChange(e){
           let phNo=ReactDOM.findDOMNode(this.refs["phno"]).value.trim();
           if(!phonenoRegex.test(phNo))
                {
                 this.setState({message: "Entera valid Phone Number."});
                }
            else
                this.setState({message: ""});

                 this.setState({phNo: phNo});

        }

        handleFind() {

                if(!phonenoRegex.test(this.state.phNo) || this.state.phNo=="")
                {
                 this.setState({message: "Entera valid Phone Number."});
                 return;
                }


            this.handlePageNation(1);
      
		}

        handlePageNation(nextPage) {

        //let phNo=ReactDOM.findDOMNode(this.refs["phno"]).value.trim();
        let phNo = this.state.phNo.replace(/\s/g, '');
            phNo=phNo.replace("-","");
            phNo=phNo.replace("(","");
            phNo=phNo.replace(")","");
    		rest({
    	           method: 'GET',
    	           path: "/combinations/lettercombinations/"+phNo+"/"+nextPage,                                
    	    }).then(response => {
    	    	let result;
    	    	if(response.status.code === 200 ) {
    	        	result =  JSON.parse(response.entity);
                    console.log("user>>>"+result.list)

    	        	this.setState({list: result.list,
                                   pageInfo: { totalElements: result.totalRecords,
                                               currentPage: result.currentPage,
                                               totalPages: result.totalPages
                                             }
                                 });
    	    	}

    	    });
		}

        handleNavFirst(e){
            e.preventDefault();
            this.handlePageNation(1);
        }
    
        handleNavPrev(e) {
            e.preventDefault();
            this.handlePageNation(this.state.pageInfo.currentPage-1);
        }
    
        handleNavNext(e) {
            e.preventDefault();
            this.handlePageNation(this.state.pageInfo.currentPage+1);
        }
    
        handleNavLast(e) {
            e.preventDefault();
            this.handlePageNation(this.state.pageInfo.totalPages);
        }
        componentDidMount() {
        }
        generateNavLinks() {
        
            let navLinks = [];
                
            if ( this.state.pageInfo.totalPages > 1) {
                if(this.state.pageInfo.currentPage > 0)
                navLinks.push(<Pagination.First key="first" onClick={this.handleNavFirst}/>);
                else
                navLinks.push(<Pagination.First key="first" onClick={this.handleNavFirst} disabled/>);
                if(this.state.pageInfo.currentPage > 0)
                navLinks.push(<Pagination.Prev  key="prev" onClick={this.handleNavPrev} />);
                else
                navLinks.push(<Pagination.Prev  key="prev" onClick={this.handleNavPrev} disabled/>);
            if (this.state.pageInfo.currentPage < this.state.pageInfo.totalPages-1) {
                navLinks.push(<Pagination.Next key="next" onClick={this.handleNavNext} />);
            }
            else
            navLinks.push(<Pagination.Next key="next" disabled/>);
            if ( this.state.pageInfo.currentPage < this.state.pageInfo.totalPages) {
                navLinks.push(<Pagination.Last key="last" onClick={this.handleNavLast} />);
            }
            else
            navLinks.push(<Pagination.Last key="last" onClick={this.handleNavLast} disabled/>);
            }
            return navLinks;
        }

        render() {
            var navLinks = this.generateNavLinks();
            var paginationNavi = <Pagination>{navLinks}</Pagination>;
            var searchList;
            var studyList = this.state.list.map((element, index) =>
            <tr key={index}>
                <td>
                    {element}
				</td>
            </tr>
            );
            var pageInfo = this.state.pageInfo;
            var paginationInfo = this.state.pageInfo.totalElements > 0 ?
                <div>
                Total Combinations : {
                    pageInfo.totalElements
                },
                {
                    pageInfo.currentPage 
                } of {
                    pageInfo.totalPages
                }
            Pages
                </div> : <div> </div>;

            return ( 
            
                <div class="col-centered"> 

                <div>
					<p key="1"><input type="text" placeholder="Enter Phone Number Here" ref="phno" className="field"  onChange={this.onPhonoChange}/></p>
                    {this.state.message!="" && <div>{this.state.message}</div>}
	                <button onClick={this.handleFind}>Find Combinations</button>
				</div>

                {this.state.list.length > 0 && 
                    <div> 
                        <table className="table table-striped">
                            <thead>
                                <tr><th width="200"> Total number of combinations: {this.state.pageInfo.totalElements}</th></tr>
                            </thead>
                            <tbody>
                                {studyList }
                            </tbody>
                        </table>
 
                                {paginationNavi }
                                {paginationInfo }
                </div>
                }
                </div>
                );
            }
        }