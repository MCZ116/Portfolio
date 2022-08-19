package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class DragAndDrop {

    WebDriver driver;
    //js code from other user
    String JS_DRAG_AND_DROP = "function h(a,b,c,d){var k=l.createEvent('DragEvent');k.initMouseEvent(b,!0,!0,l.defaultView,0,0,0,m,n,w,x,y,!1,0,null);Object.setPrototypeOf(k,null);k.dataTransfer=g;Object.setPrototypeOf(k,DragEvent.prototype);a.dispatchEvent(k);setTimeout(d,c)}var a=arguments,c=a[0],d=a[1],q=a[2]||0,r=a[3]||0,t=a[4]||1;a=a[5]||'';var x='alt'===a||'\ue00a'===a,w='ctrl'===a||'\ue009'===a,y='shift'===a||'\ue008'===a,l=c.ownerDocument;a=c.getBoundingClientRect();var e=d?d.getBoundingClientRect():a,m=a.left+a.width/2,n=a.top+a.height/2,u=e.left+(q?q:e.width/2),v=e.top+(r?r:e.height/2),p=l.elementFromPoint(m,n),f=l.elementFromPoint(u,v);for(d=p;d&&!d.draggable;)d=d.parentElement;if(!d||!c.contains(p))throw c=Error('source element is not interactable/draggable'),c.code=15,c;if(!f)throw c=Error('target element is not interactable'),c.code=15,c;var g={constructor:DataTransfer,effectAllowed:null,dropEffect:null,types:[],files:Object.setPrototypeOf([],null),_items:Object.setPrototypeOf([],{add:function(a,b){this[this.length]={_data:''+_data,kind:'string',type:b,getAsFile:function(){},getAsString:function(a){a(this._data)}};g.types.push(b)},remove:function(a){Array.prototype.splice.call(this,a&65535,1);g.types.splice(a&65535,1)},clear:function(a,b){this.length=0;g.types.length=0}}),setData:function(a,b){this.clearData(a);this._items.add(b,a)},getData:function(a){for(var b=this._items.length;b--&&this._items[b].type!==a;);return 0<=b?this._items[b]._data:null},clearData:function(a){for(var b=this._items.length;b--&&this._items[b].type!==a;);this._items.remove(b)},setDragImage:function(a){}};'items'in DataTransfer.prototype&&(g.items=g._items);e=f.getBoundingClientRect();h(p,'dragstart',t,function(){var a=f.getBoundingClientRect();m=a.left+u-e.left;n=a.top+v-e.top;h(f,'dragenter',1,function(){h(f,'dragover',t,function(){f=l.elementFromPoint(m,n);h(f,'drop',1,function(){h(p,'dragend',1,function(){})})})})})";

    @BeforeTest
    public void setup(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(description = "Drag objects on each other")
    public void DragAndDropTest(){
        WebElement toDrag = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]"));
        WebElement toDrop = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]"));

        // Actions drag and drop doesn't work on this website so used js code from other user
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(JS_DRAG_AND_DROP,toDrag,toDrop);

    }

}
