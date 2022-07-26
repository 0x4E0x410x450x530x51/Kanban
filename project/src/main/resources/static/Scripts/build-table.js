var table_row;
var current_size;
var last_table_el;

function build(){
    table_row = document.getElementById("board-table").children[0].children[0];
    current_size = table_row.childElementCount + departments.length;
    
    //last element in the table row get removed for later usage
    last_table_el = table_row.lastElementChild;

    table_row.removeChild(table_row.lastChild)



    //iterate and create all needed collumns for the kanban table
    for(let i = 0; i < departments.length; i++){
        let td = document.createElement("td");
        let div_wrapper = document.createElement("div");
        
        
        let title;
        let case_el = 0
        do{
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
            div.setAttribute("id", Boolean(case_el) ? "done":"inprogress" );

            //define inner elements inside the div
            h2.setAttribute("class","task-headers");
            innerDiv.setAttribute("class", Boolean(case_el) ? "doneTitle": "inprogressTitle");
            innerDiv.innerHTML = title;
            
            //append everything
            h2.appendChild(innerDiv);
            strong.appendChild(h2);
            div.appendChild(strong);
            div_wrapper.appendChild(div)

            case_el++;
        }while(case_el < 2);
        td.appendChild(div_wrapper);
        table_row.appendChild(td);
    }
    table_row.appendChild(last_table_el);
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