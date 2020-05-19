import React from 'react';
import ReactDOM, {render} from 'react-dom';
import {IntlProvider, addLocaleData, defineMessages} from 'react-intl';

import HomeApp from './component/home/homeApp';

/**
 *  
 * registering components corresponding element id in JSP page.
 * 
 */
var selectedModules = [];
//for home module
var elementId = 'homeData';

if(document.getElementById(elementId) != null || 
		   document.getElementById(elementId) != undefined)
{
	const selectedModule =<HomeApp searchFilter={false}/>; 
	selectedModules.push({'elementId': elementId, 'module': selectedModule});		
}

selectedModules.forEach( m => {
	ReactDOM.render(m.module, document.getElementById(m.elementId));
});