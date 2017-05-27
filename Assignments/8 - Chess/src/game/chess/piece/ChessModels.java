package game.chess.piece;

import java.awt.Polygon;

class ChessModels 
{	
	public static ChessModels.Model create(ChessPiece.Type type)
	{
		switch(type)
		{
			case KING:
				return new King();
			case QUEEN:
				return new Queen();
			case ROOK:
				return new Rook();
			case KNIGHT:
				return new Knight();
			case BISHOP:
				return new Bishop();
			case PAWN:
				return new Pawn();
			default:
				return null;
		}
	}
	
	static abstract class Model extends Polygon
	{
		public Model()
		{
			super();
			createModel();
		}
		
		protected abstract void createModel();
	}
	
	static class King extends Model
	{
		protected void createModel()
		{
			this.addPoint((int)38.48342176846097,(int)53.35122224262784);
			this.addPoint((int)43.96942050116405,(int)51.01825727735249);
			this.addPoint((int)43.96942050116405,(int)38.19214919635229);
			this.addPoint((int)49.03742803846089,(int)34.95186444691251);
			this.addPoint((int)52.26954780306136,(int)30.85945424011777);
			this.addPoint((int)53.62841449465071,(int)27.137824837650584);
			this.addPoint((int)53.569405896323076,(int)23.458653739520486);
			this.addPoint((int)51.18574803216117,(int)19.622960737773354);
			this.addPoint((int)46.30914463315693,(int)17.374854905264726);
			this.addPoint((int)42.264676570892334,(int)16.951334953308105);
			this.addPoint((int)37.97447047914778,(int)18.160765988486162);
			this.addPoint((int)32.901389735085644,(int)20.68872329166959);
			this.addPoint((int)32.47836322443828,(int)16.690568889890415);
			this.addPoint((int)31.650163922991084,(int)14.223828111376093);
			this.addPoint((int)30.222949010985246,(int)12.572820322854199);
			this.addPoint((int)28.624050796031952,(int)11.230842181614491);
			this.addPoint((int)28.624050796031952,(int)5.3470258712768555);
			this.addPoint((int)30.7669080240386,(int)5.3470258712768555);
			this.addPoint((int)30.7669080240386,(int)3.204169069017695);
			this.addPoint((int)28.624050796031952,(int)3.204169069017695);
			this.addPoint((int)28.624050796031952,(int)0.3470255306789056);
			this.addPoint((int)25.94737777539663,(int)0.3470255306789056);
			this.addPoint((int)25.94737777539663,(int)3.204169069017695);
			this.addPoint((int)23.804520547389984,(int)3.204169069017695);
			this.addPoint((int)23.804520547389984,(int)5.3470258712768555);
			this.addPoint((int)25.94737777539663,(int)5.3470258712768555);
			this.addPoint((int)25.94737777539663,(int)11.230842181614491);
			this.addPoint((int)24.348479560443337,(int)12.572820322854199);
			this.addPoint((int)22.9212646484375,(int)14.223828111376093);
			this.addPoint((int)22.09306534699033,(int)16.690568889890415);
			this.addPoint((int)21.670038836342968,(int)20.68872329166959);
			this.addPoint((int)16.5969580922808,(int)18.160765988486162);
			this.addPoint((int)12.30675200053625,(int)16.951334953308105);
			this.addPoint((int)8.262283938271679,(int)17.374854905264726);
			this.addPoint((int)3.385680539267412,(int)19.622960737773354);
			this.addPoint((int)1.0020226751055077,(int)23.458653739520486);
			this.addPoint((int)0.943014076777871,(int)27.137824837650584);
			this.addPoint((int)2.3018807683672264,(int)30.85945424011777);
			this.addPoint((int)5.534000532967724,(int)34.95186444691251);
			this.addPoint((int)10.60200807026456,(int)38.19214919635229);
			this.addPoint((int)10.60200807026456,(int)51.01825727735249);
			this.addPoint((int)16.08800680296764,(int)53.35122224262784);
			this.addPoint((int)22.196075371333535,(int)54.80198567254203);
			this.addPoint((int)27.285714285714306,(int)55.07667793546406);
			this.addPoint((int)32.37535320009505,(int)54.80198567254203);
		}
	}
	
	static class Queen extends Model
	{
			protected void createModel()
			{
				this.addPoint((int)26.5,(int)53.7142860548837);
				this.addPoint((int)20.25,(int)53.7142860548837);
				this.addPoint((int)15.016771793365479,(int)53.62884535108296);
				this.addPoint((int)9.93113660812378,(int)52.56434897014074);
				this.addPoint((int)8.90928202867508,(int)51.43533992767334);
				this.addPoint((int)11.554929554462433,(int)45.1251358304705);
				this.addPoint((int)12.551605641841888,(int)43.996126788003124);
				this.addPoint((int)12.626366257667542,(int)38.49852899142675);
				this.addPoint((int)9.635950565338135,(int)37.21692184039526);
				this.addPoint((int)7.617419898509979,(int)32.944899422781816);
				this.addPoint((int)4.28891921043396,(int)14.153288568769199);
				this.addPoint((int)2.4890466928482056,(int)13.873137371880688);
				this.addPoint((int)1.3063117265701294,(int)12.902950014386874);
				this.addPoint((int)0.7064893245697021,(int)11.386895656585693);
				this.addPoint((int)0.8503004312515259,(int)9.731197834014893);
				this.addPoint((int)1.6992076635360718,(int)8.379497323717402);
				this.addPoint((int)3.025759696960449,(int)7.6939826011657715);
				this.addPoint((int)4.4744952917099,(int)7.858339854649159);
				this.addPoint((int)5.657233238220215,(int)8.82852806363789);
				this.addPoint((int)6.2570571303367615,(int)10.344582421439043);
				this.addPoint((int)6.113244533538818,(int)12.000280244009843);
				this.addPoint((int)5.2643298506736755,(int)13.351980754307363);
				this.addPoint((int)5.163457930088043,(int)13.716309036527377);
				this.addPoint((int)14.194407999515533,(int)31.32153030804227);
				this.addPoint((int)14.340951979160309,(int)9.307669367109042);
				this.addPoint((int)12.876939713954926,(int)8.410282271248974);
				this.addPoint((int)12.0063796043396,(int)7.076689652034219);
				this.addPoint((int)11.835898399353027,(int)5.424300738743398);
				this.addPoint((int)12.411178588867188,(int)3.8958724566868455);
				this.addPoint((int)13.578073620796204,(int)2.9009432111467675);
				this.addPoint((int)15.023915410041809,(int)2.7061092512948335);
				this.addPoint((int)16.361290901899338,(int)3.3635740280151367);
				this.addPoint((int)17.231851011514664,(int)4.697168350219727);
				this.addPoint((int)17.402331471443176,(int)6.349557263510576);
				this.addPoint((int)16.827050536870956,(int)7.877985545567128);
				this.addPoint((int)15.66015550494194,(int)8.87291138512748);
				this.addPoint((int)15.593497395515442,(int)9.168691771371044);
				this.addPoint((int)21.89472870528698,(int)30.467125611645855);
				this.addPoint((int)25.857029795646667,(int)7.1418818065098435);
				this.addPoint((int)25.12084984779358,(int)6.420440810067333);
				this.addPoint((int)24.091403499245644,(int)5.243931974683505);
				this.addPoint((int)23.714598953723907,(int)3.6367897987365723);
				this.addPoint((int)24.091402009129524,(int)2.0296476227896676);
				this.addPoint((int)25.1208476126194,(int)0.8531353814261422);
				this.addPoint((int)26.5,(int)0.4225020408630371);
				this.addPoint((int)27.8791523873806,(int)0.8531353814261422);
				this.addPoint((int)28.908597990870476,(int)2.0296476227896676);
				this.addPoint((int)29.285401046276093,(int)3.6367897987365723);
				this.addPoint((int)28.908596500754356,(int)5.243931974683505);
				this.addPoint((int)27.87915015220642,(int)6.420440810067333);
				this.addPoint((int)27.142970204353333,(int)7.1418818065098435);
				this.addPoint((int)31.10527129471302,(int)30.467125611645855);
				this.addPoint((int)37.40650260448456,(int)9.168691771371044);
				this.addPoint((int)37.33984449505806,(int)8.87291138512748);
				this.addPoint((int)36.172949463129044,(int)7.877985545567128);
				this.addPoint((int)35.597668528556824,(int)6.349557263510576);
				this.addPoint((int)35.768148988485336,(int)4.697168350219727);
				this.addPoint((int)36.63870909810066,(int)3.3635740280151367);
				this.addPoint((int)37.97608458995819,(int)2.7061092512948335);
				this.addPoint((int)39.421926379203796,(int)2.9009432111467675);
				this.addPoint((int)40.58882141113281,(int)3.8958724566868455);
				this.addPoint((int)41.16410160064697,(int)5.424300738743398);
				this.addPoint((int)40.9936203956604,(int)7.076689652034219);
				this.addPoint((int)40.123060286045074,(int)8.410282271248974);
				this.addPoint((int)38.65904802083969,(int)9.307669367109042);
				this.addPoint((int)38.80559200048447,(int)31.32153030804227);
				this.addPoint((int)47.83654206991196,(int)13.716309036527377);
				this.addPoint((int)47.735670149326324,(int)13.351980754307363);
				this.addPoint((int)46.88675546646118,(int)12.000280244009843);
				this.addPoint((int)46.74294286966324,(int)10.344582421439043);
				this.addPoint((int)47.342766761779785,(int)8.82852806363789);
				this.addPoint((int)48.5255047082901,(int)7.858339854649159);
				this.addPoint((int)49.97424030303955,(int)7.6939826011657715);
				this.addPoint((int)51.30079233646393,(int)8.379497323717402);
				this.addPoint((int)52.149699568748474,(int)9.731197834014893);
				this.addPoint((int)52.2935106754303,(int)11.386895656585693);
				this.addPoint((int)51.69368827342987,(int)12.902950014386874);
				this.addPoint((int)50.510953307151794,(int)13.873137371880688);
				this.addPoint((int)48.71108078956604,(int)14.153288568769199);
				this.addPoint((int)45.38258010149002,(int)32.944899422781816);
				this.addPoint((int)43.364049434661865,(int)37.21692184039526);
				this.addPoint((int)40.37363374233246,(int)38.49852899142675);
				this.addPoint((int)40.44839435815811,(int)43.996126788003124);
				this.addPoint((int)41.44507044553757,(int)45.1251358304705);
				this.addPoint((int)44.09071797132492,(int)51.43533992767334);
				this.addPoint((int)43.06886339187622,(int)52.56434897014074);
				this.addPoint((int)37.98322820663452,(int)53.62884535108296);
				this.addPoint((int)32.75,(int)53.7142860548837);
			}
	}
	
	static class Rook extends Model
	{
			protected void createModel()
			{
				this.addPoint((int)50.0,(int)55.0);
				this.addPoint((int)50.0,(int)50.0);
				this.addPoint((int)45.0,(int)50.0);
				this.addPoint((int)45.0,(int)42.0);
				this.addPoint((int)40.0,(int)37.0);
				this.addPoint((int)40.0,(int)13.0);
				this.addPoint((int)45.0,(int)8.0);
				this.addPoint((int)45.0,(int)0.0);
				this.addPoint((int)37.0,(int)0.0);
				this.addPoint((int)37.0,(int)5.0);
				this.addPoint((int)29.0,(int)5.0);
				this.addPoint((int)29.0,(int)0.0);
				this.addPoint((int)21.0,(int)0.0);
				this.addPoint((int)21.0,(int)5.0);
				this.addPoint((int)13.0,(int)5.0);
				this.addPoint((int)13.0,(int)0.0);
				this.addPoint((int)5.0,(int)0.0);
				this.addPoint((int)5.0,(int)8.0);
				this.addPoint((int)11.0,(int)13.0);
				this.addPoint((int)11.0,(int)37.0);
				this.addPoint((int)5.0,(int)42.0);
				this.addPoint((int)5.0,(int)50.0);
				this.addPoint((int)0.0,(int)50.0);
				this.addPoint((int)0.0,(int)55.0);
			}
	}
	
	static class Knight extends Model
	{
			protected void createModel()
			{
				this.addPoint((int)15.7539908545358,(int)44.405294241649784);
				this.addPoint((int)18.53080436161588,(int)41.13007685967855);
				this.addPoint((int)22.019624301365468,(int)37.214056594031206);
				this.addPoint((int)26.220446416309926,(int)32.23003029823303);
				this.addPoint((int)28.0004565375192,(int)27.103602545602);
				this.addPoint((int)28.498859933444436,(int)21.83477503912792);
				this.addPoint((int)29.0684632062912,(int)16.49474552699499);
				this.addPoint((int)27.3670439379556,(int)21.94089344569616);
				this.addPoint((int)22.50945782661438,(int)26.97970662798201);
				this.addPoint((int)18.77010679244995,(int)30.358004229409374);
				this.addPoint((int)14.505643095288974,(int)33.22345920971463);
				this.addPoint((int)13.410007544926259,(int)34.3110191822052);
				this.addPoint((int)12.840405123574413,(int)35.450225727898754);
				this.addPoint((int)12.484403610229492,(int)36.51823111942838);
				this.addPoint((int)11.210895878928056,(int)37.790789008140564);
				this.addPoint((int)10.450497286660351,(int)37.998331955501015);
				this.addPoint((int)9.950073719024658,(int)37.43420422077179);
				this.addPoint((int)9.759277548108798,(int)36.5737409251077);
				this.addPoint((int)9.90977927616666,(int)34.21507954597473);
				this.addPoint((int)8.69937242780415,(int)32.93347324643818);
				this.addPoint((int)7.6497244153704,(int)33.783485719135854);
				this.addPoint((int)7.427407605307451,(int)34.80665394238065);
				this.addPoint((int)7.611293043409091,(int)35.78663434301106);
				this.addPoint((int)6.7529520988464355,(int)36.58344030380249);
				this.addPoint((int)5.3932390213012695,(int)36.932583877018544);
				this.addPoint((int)3.4510710580008492,(int)36.36669397354126);
				this.addPoint((int)1.9142827987670898,(int)35.37877593721663);
				this.addPoint((int)1.0511632646833107,(int)34.05233246939525);
				this.addPoint((int)0.9271242959158883,(int)32.410332645688754);
				this.addPoint((int)1.2831241062709466,(int)30.416722467967446);
				this.addPoint((int)2.67950425829207,(int)26.662657601492754);
				this.addPoint((int)4.395872797284824,(int)23.689680099487305);
				this.addPoint((int)7.705357687813915,(int)17.88312843867712);
				this.addPoint((int)10.395012174333857,(int)12.526687213352744);
				this.addPoint((int)13.60641847337996,(int)8.36078916277205);
				this.addPoint((int)13.338006837027422,(int)6.015707424708779);
				this.addPoint((int)13.638664586203447,(int)2.176374707903193);
				this.addPoint((int)14.174394539424355,(int)1.8502793993268654);
				this.addPoint((int)16.226282732827343,(int)4.335537637983066);
				this.addPoint((int)17.16643701280867,(int)6.295975276402061);
				this.addPoint((int)21.36725912775313,(int)6.295975276402061);
				this.addPoint((int)22.01810864039831,(int)4.616207395281123);
				this.addPoint((int)24.177934885025024,(int)1.1105959756033883);
				this.addPoint((int)25.463021244321567,(int)0.9437029702322945);
				this.addPoint((int)26.681932823998608,(int)4.010515213012695);
				this.addPoint((int)26.849689245224,(int)5.583972249712275);
				this.addPoint((int)33.96972717557637,(int)6.082376071384971);
				this.addPoint((int)38.81135286603657,(int)8.36078916277205);
				this.addPoint((int)42.869773524148144,(int)11.280003275190097);
				this.addPoint((int)46.700594697679804,(int)15.782742500305176);
				this.addPoint((int)49.05020724024092,(int)21.407573223114014);
				this.addPoint((int)50.260613237108515,(int)26.60520042691914);
				this.addPoint((int)51.11501686913627,(int)31.731627328055254);
				this.addPoint((int)51.7558195931571,(int)37.14285629136222);
				this.addPoint((int)52.111822809491855,(int)41.55727910143989);
				this.addPoint((int)52.32542371749878,(int)44.76129612752371);
				this.addPoint((int)52.53902462550573,(int)48.10771397181921);
				this.addPoint((int)52.53902462550573,(int)51.428571428571445);
				this.addPoint((int)14.401183400835322,(int)51.428571428571445);
			}
	}
	
	static class Bishop extends Model
	{
			protected void createModel()
			{
				this.addPoint((int)49.08695652173913,(int)49.03225806451613);
				this.addPoint((int)46.913043478260875,(int)47.41935483870968);
				this.addPoint((int)42.565217391304344,(int)45.80645161290323);
				this.addPoint((int)33.869565217391305,(int)47.41935483870968);
				this.addPoint((int)31.695652173913043,(int)47.41935483870968);
				this.addPoint((int)27.347826086956523,(int)45.80645161290323);
				this.addPoint((int)25.17391304347826,(int)44.193548387096776);
				this.addPoint((int)31.695652173913043,(int)42.58064516129032);
				this.addPoint((int)33.869565217391305,(int)40.96774193548387);
				this.addPoint((int)36.04347826086956,(int)37.74193548387097);
				this.addPoint((int)33.869565217391305,(int)34.516129032258064);
				this.addPoint((int)29.52173913043478,(int)31.29032258064516);
				this.addPoint((int)33.869565217391305,(int)29.67741935483871);
				this.addPoint((int)36.04347826086956,(int)28.064516129032256);
				this.addPoint((int)38.21739130434783,(int)24.838709677419356);
				this.addPoint((int)38.21739130434783,(int)21.612903225806452);
				this.addPoint((int)36.04347826086956,(int)16.774193548387096);
				this.addPoint((int)31.695652173913043,(int)13.548387096774192);
				this.addPoint((int)25.17391304347826,(int)10.322580645161288);
				this.addPoint((int)29.52173913043478,(int)8.70967741935484);
				this.addPoint((int)31.695652173913043,(int)5.483870967741936);
				this.addPoint((int)31.695652173913043,(int)3.8709677419354875);
				this.addPoint((int)29.52173913043478,(int)0.6451612903225765);
				this.addPoint((int)25.17391304347826,(int)-0.9677419354838719);
				this.addPoint((int)23.0,(int)-0.9677419354838719);
				this.addPoint((int)18.652173913043477,(int)0.6451612903225765);
				this.addPoint((int)16.47826086956522,(int)3.8709677419354875);
				this.addPoint((int)16.47826086956522,(int)5.483870967741936);
				this.addPoint((int)18.652173913043477,(int)8.70967741935484);
				this.addPoint((int)23.0,(int)10.322580645161288);
				this.addPoint((int)16.47826086956522,(int)13.548387096774192);
				this.addPoint((int)12.130434782608695,(int)16.774193548387096);
				this.addPoint((int)9.956521739130437,(int)21.612903225806452);
				this.addPoint((int)9.956521739130437,(int)24.838709677419356);
				this.addPoint((int)12.130434782608695,(int)28.064516129032256);
				this.addPoint((int)14.304347826086953,(int)29.67741935483871);
				this.addPoint((int)18.652173913043477,(int)31.29032258064516);
				this.addPoint((int)14.304347826086953,(int)34.516129032258064);
				this.addPoint((int)12.130434782608695,(int)37.74193548387097);
				this.addPoint((int)14.304347826086953,(int)40.96774193548387);
				this.addPoint((int)16.47826086956522,(int)42.58064516129032);
				this.addPoint((int)23.0,(int)44.193548387096776);
				this.addPoint((int)20.826086956521742,(int)45.80645161290323);
				this.addPoint((int)16.47826086956522,(int)47.41935483870968);
				this.addPoint((int)14.304347826086953,(int)47.41935483870968);
				this.addPoint((int)5.608695652173914,(int)45.80645161290323);
				this.addPoint((int)1.2608695652173907,(int)47.41935483870968);
				this.addPoint((int)-0.9130434782608745,(int)49.03225806451613);
			}
	}
	
	static class Pawn extends Model
	{
			protected void createModel()
			{
				this.addPoint((int)50.0,(int)50.0);
				this.addPoint((int)47.0,(int)40.0);
				this.addPoint((int)43.0,(int)35.0);
				this.addPoint((int)39.0,(int)33.0);
				this.addPoint((int)31.0,(int)31.0);
				this.addPoint((int)39.0,(int)28.0);
				this.addPoint((int)43.0,(int)23.0);
				this.addPoint((int)43.0,(int)19.0);
				this.addPoint((int)39.0,(int)14.0);
				this.addPoint((int)31.0,(int)12.0);
				this.addPoint((int)35.0,(int)9.0);
				this.addPoint((int)35.0,(int)2.0);
				this.addPoint((int)31.0,(int)0.0);
				this.addPoint((int)20.0,(int)0.0);
				this.addPoint((int)16.0,(int)2.0);
				this.addPoint((int)16.0,(int)9.0);
				this.addPoint((int)20.0,(int)12.0);
				this.addPoint((int)12.0,(int)14.0);
				this.addPoint((int)8.0,(int)19.0);
				this.addPoint((int)8.0,(int)23.0);
				this.addPoint((int)12.0,(int)28.0);
				this.addPoint((int)20.0,(int)31.0);
				this.addPoint((int)12.0,(int)33.0);
				this.addPoint((int)8.0,(int)35.0);
				this.addPoint((int)4.0,(int)40.0);
				this.addPoint((int)0.0,(int)50.0);

			}
	}
}
