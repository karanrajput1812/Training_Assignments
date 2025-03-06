import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'money'
})
export class MoneyPipe implements PipeTransform {

  transform(value: number, ...args: string[]): string {
    if(args[0]=='USD') {
      return "$ " + value/87;
    }
    else if(args[0]=='JPY') {
      return "¥ " + value/0.58;
    }
    else if(args[0]=='SAR') {
      return "SAR  " + value/23.22;
    }
    else if(args[0]=='EUR') {
      return "€ " + value/94;
    }
    else if(args[0]=='AUD') {
      return "AU$ " + value/55;
    }
    return "Rs.. " + value;
  }

}
