class CardEditor extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            "front": "",
            "back": ""
        }
    }
    render(){
        const rows = this.props.cards.map((card,i)=>{
            return (
                <tr key={i}>
                    <td>{card.front}</td>
                    <td>{card.back}</td>
                    <td><button onClick = {() => this.props.deleteCard(i)}>Delete</button></td>
                </tr>
            );
        });
        return (
            <div>
                This is editor!
                <h1>Card Editor</h1>
                <table>
                    <thead>
                        <tr>
                            <th> Front </th>
                            <th> Back </th>
                            <th> Delete </th>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                    </tbody>
                </table>
                <input name = "front" placeholder = "Front of card" value = {this.state.front} onChange = {this.handleChange} />
                <input name = "back" placeholder = "Back of card" value = {this.state.back}  onChange = {this.handleChange} />
                <button onClick = {this.addCard}>Add</button>
                <hr/>
                <button onClick = {this.props.switchMode}>Go to viewer</button>
            </div>
        );
    }
    addCard = () =>{
        this.props.addCard(this.state.front, this.state.back);
        this.setState({
            "back": "",
            "front":""
        });
    }
    handleChange = (event) =>{
        this.setState({
            [event.target.name]: event.target.value
        });
    }

}
class CardViewer extends React.Component{
    render(){
        return (
            <div>
                This is viewer!
                <hr />
                <button onClick = {this.props.switchMode}>Go to editor</button>
            </div>
        );
    }
}
class App extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            editor: true,
            cards: []
        }
    }
    render(){
        if (this.state.editor){
            return (
                <CardEditor
                    switchMode = {this.switchMode}
                    cards = {this.state.cards}
                    addCard = {this.addCard}
                    deleteCard = {this.deleteCard}
                />
                
            );
        } else{
            return (
                <div>
                    <CardViewer
                        switchMode = {this.switchMode}
                        cards = {this.state.cards}
                        
                    />
                </div>
            );

        }
    }
    switchMode = () =>{
        this.setState(oldState=>({
            editor: !oldState.editor,
        }));
    }
    addCard = (front,back)=>{
        this.setState(oldState=>({
            cards: [...oldState.cards, {front, back}]
        }));
    }
    deleteCard = (index) =>{
        let newCards = [];
        for (let i = 0; i<this.state.cards.length; i++){
            if (i!=index){
                newCards.push(this.state.cards[i]);
            }
        }
        this.setState(oldState=>({
            cards: newCards
        }));
    }
}

    

ReactDOM.render(<App />, document.querySelector("#app"));