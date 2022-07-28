var table_row;
var current_size;
var last_table_el;

function buildMain(){
    table_row = document.getElementById("board-table").children[0].children[1];
    //iterate and create all needed collumns for the kanban table
    for(let i = 0; i < departments.length; i++){
       
        let div_wrapper = document.createElement("div");
        
        
        let title;
        let case_el = 0
        do{
            let td = document.createElement("td");
            switch(case_el){
                case 0:
                    title = "Doing";
                    break;
                case 1:
                    title = "Done";
                    break;
                default:
                    console.error("something really messed up");
                    alert("check console for errors ctrl+shift+i");
                    return;
                break;
            }
            let div = document.createElement("div");
            let strong = document.createElement("strong");
            let h2 = document.createElement("h2");
            let innerDiv = document.createElement("div");

            //define wrapper
            div_wrapper.setAttribute("class", "kanban-group");

            //define td
            td.setAttribute("style", "height:inherit;")

            //define class and id
            //REMIND    ID is probably irrelevant in current state
            div.setAttribute("class", "kanban-block story-table done-doing-width");
            

            //define inner elements inside the div
            h2.setAttribute("class","task-headers");
            innerDiv.setAttribute("class", Boolean(case_el) ? "doneTitle": "inprogressTitle");
            innerDiv.innerHTML = title;
            
            //append everything
            h2.appendChild(innerDiv);
            strong.appendChild(h2);
            div.appendChild(strong);
            
            td.appendChild(div);
            table_row.appendChild(td);
            case_el++;
        }while(case_el < 2);
        
        
    }
}

function buildHeaders(){
   table_row = document.getElementById("board-table").children[0].children[0];
    //last element in the table row get removed for later usage
    last_table_el = table_row.lastElementChild;

    table_row.removeChild(table_row.lastChild);

     for(let i = 0; i < departments.length; i++){
        
        let td = document.createElement("td");
        let strong = document.createElement("strong");
        let h2 = document.createElement("h2");
        let div = document.createElement("div");

        td.setAttribute("colspan","2"); 
        h2.setAttribute("class","task-headers");
        div.setAttribute("class", "title");
        div.innerHTML = departments[i].depName;

        h2.appendChild(div);
        strong.appendChild(h2);
        td.appendChild(strong);
        table_row.appendChild(td);
    }
    table_row.appendChild(last_table_el);
}

function buildCollumns(){
    table_row = document.getElementById("board-table").children[0].children[2];
    let j = 0, k = 0;
    let td;
    let div;
    let swt = 0;
    td = document.createElement("td");
    div = document.createElement("div");
    div.setAttribute("id","backlog");
    td.appendChild(div)
    table_row.appendChild(td);

    for(let i = 0; i < departments.length*2; i++){
        td = document.createElement("td");
        div = document.createElement("div");

        if(!swt){
            div.setAttribute("id", "inprogress-"+(j+1));
            div.setAttribute("group-name",departments[j].depName);
            j++;
            swt = 1;
        }else {
            div.setAttribute("id", "done-"+( k+1));
            div.setAttribute("group-name",departments[k].depName);
            k++;
            swt = 0;
        }
        td.appendChild(div);
        table_row.appendChild(td);
    }


}

function build(){
    current_size =  departments.length;
    buildHeaders();
    buildMain(); 
    buildCollumns();
}


//REMIND This is just the structure of the generated Elements 
/*
<td>
    <div class="kanban-group ">
        <div class="kanban-block story-table  " id="inprogress">
            <strong>
                <h2 class="task-headers">
                    <div class="inprogressTitle">Doing</div>
                </h2>
            </strong>
        </div>
        <div class="kanban-block story-table  " id="done" class="done" ondrop="drop(event)"
        ondragover="allowDrop(event)">
        <strong>
            <h2 class="task-headers">
                <div class="doneTitle">Done</div>
            </h2>
        </strong>
        </div>
    </div>
</td>
*/