let resource:{labels:any, messages:any};
module gi.com{
    export class Resource{
        static getTxt(id:string):string{
            let text:string =  resource.labels[id];
            if(text &&text.length>0){
                return text;
            }
            return id;
        }
        static getMsg(id:string):string{
            let text:string =  resource.messages[id];
            if(text &&text.length>0){
                return text;
            }
            return id;
        }
    }
}
