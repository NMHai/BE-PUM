0x00401000:	movl %eax, $0x404858<UINT32>
0x00401005:	pushl %eax
0x00401006:	pushl %fs:0
0x0040100d:	movl %fs:0, %esp
0x00401014:	xorl %eax, %eax
0x00401016:	movl (%eax), %ecx
0x00404858:	movl %eax, $0xf04035dd<UINT32>
0x0040485d:	leal %ecx, 0x1000129e(%eax)
0x00404863:	movl 0x1(%ecx), %eax
0x00404866:	movl %edx, 0x4(%esp)
0x0040486a:	movl %edx, 0xc(%edx)
0x0040486d:	movb (%edx), $0xffffffe9<UINT8>
0x00404870:	addl %edx, $0x5<UINT8>
0x00404873:	subl %ecx, %edx
0x00404875:	movl -4(%edx), %ecx
0x00404878:	xorl %eax, %eax
0x0040487a:	ret

0x00401016:	jmp 0x0040487b
0x0040487b:	movl %eax, $0xf04035dd<UINT32>
0x00404880:	popl %fs:0
0x00404887:	addl %esp, $0x4<UINT8>
0x0040488a:	pushl %ebp
0x0040488b:	pushl %ebx
0x0040488c:	pushl %ecx
0x0040488d:	pushl %edi
0x0040488e:	pushl %esi
0x0040488f:	pushl %edx
0x00404890:	leal %ebx, 0x10001257(%eax)
0x00404896:	movl %edx, 0x18(%ebx)
0x00404899:	pushl %edx
0x0040489a:	movl %ebp, %eax
0x0040489c:	pushl $0x40<UINT8>
0x0040489e:	pushl $0x1000<UINT32>
0x004048a3:	pushl 0x4(%ebx)
0x004048a6:	pushl $0x0<UINT8>
0x004048a8:	movl %ecx, 0x10(%ebx)
0x004048ab:	addl %ecx, %edx
0x004048ad:	movl %eax, (%ecx)
0x004048af:	call VirtualAlloc@kernel32.dll
VirtualAlloc@kernel32.dll: API Node	
0x004048b1:	popl %edx
0x004048b2:	movl %edi, %eax
0x004048b4:	pushl %eax
0x004048b5:	pushl %edx
0x004048b6:	movl %esi, (%ebx)
0x004048b8:	movl %eax, 0x20(%ebx)
0x004048bb:	addl %eax, %edx
0x004048bd:	movl %ecx, (%eax)
0x004048bf:	movl 0x20(%ebx), %ecx
0x004048c2:	movl %eax, 0x1c(%ebx)
0x004048c5:	addl %eax, %edx
0x004048c7:	movl %ecx, (%eax)
0x004048c9:	movl 0x1c(%ebx), %ecx
0x004048cc:	addl %esi, %edx
0x004048ce:	movl %ecx, 0xc(%ebx)
0x004048d1:	addl %ecx, %edx
0x004048d3:	leal %eax, 0x1c(%ebx)
0x004048d6:	pushl %eax
0x004048d7:	pushl %edi
0x004048d8:	pushl %esi
0x004048d9:	call 0x0040477b
0x0040477b:	pusha
0x0040477c:	movl %esi, 0x24(%esp)
0x00404780:	movl %edi, 0x28(%esp)
0x00404784:	cld
0x00404785:	lodsl %eax, %ds:(%esi)
0x00404786:	xorl %ecx, %ecx
0x00404788:	testl %eax, %eax
0x0040478a:	je 17
0x0040478c:	xorl %edx, %edx
0x0040478e:	leal %ebx, (%eax,%edi)
0x00404791:	movsb %es:(%edi), %ds:(%esi)
0x00404792:	movb %cl, $0x3<UINT8>
0x00404794:	call 0x0040480b
0x0040480b:	addl %edx, %edx
0x0040480d:	jne 0x00404815
0x0040480f:	xchgl %edx, %eax
0x00404810:	lodsl %eax, %ds:(%esi)
0x00404811:	xchgl %edx, %eax
0x00404812:	addl %edx, %edx
0x00404814:	incl %edx
0x00404815:	ret

0x00404799:	jae 0x00404791
0x0040479b:	cmpl %edi, %ebx
0x0040479d:	jae 0x00404828
0x004047a3:	pushl %ebx
0x004047a4:	pushl %ebp
0x004047a5:	pushl %edi
0x004047a6:	xorl %ebx, %ebx
0x004047a8:	incl %ebx
0x004047a9:	xorl %ebp, %ebp
0x004047ab:	movl %eax, %ebx
0x004047ad:	leal %edi, (%ebp,%ebx)
0x004047b1:	movl %ebp, %ebx
0x004047b3:	movl %ebx, %edi
0x004047b5:	call 0x0040480b
0x004047ba:	jae 0x004047ad
0x004047bc:	leal %ebx, (%ebp,%edi)
0x004047c0:	addl %eax, %edi
0x004047c2:	movl %ebp, %edi
0x004047c4:	call 0x0040480b
0x004047c9:	jae 0x004047ad
0x004047cb:	popl %edi
0x004047cc:	popl %ebp
0x004047cd:	popl %ebx
0x004047ce:	subl %eax, %ecx
0x004047d0:	jae 0x004047db
0x004047db:	movb %cl, $0x6<UINT8>
0x004047dd:	call 0x0040480b
0x004047e2:	adcl %eax, %eax
0x004047e4:	decl %ecx
0x004047e5:	jne 0x004047dd
0x004047e7:	incl %eax
0x004047e8:	call 0x00404816
0x00404816:	xorl %ecx, %ecx
0x00404818:	incl %ecx
0x00404819:	call 0x0040480b
0x0040481e:	adcl %ecx, %ecx
0x00404820:	call 0x0040480b
0x00404825:	jb 0x00404819
0x00404827:	ret

0x004047ed:	movl %ebp, %eax
0x004047ef:	cmpl %eax, $0x8001<UINT32>
0x004047f4:	sbbl %ecx, $0xffffffff<UINT8>
0x004047f7:	cmpl %eax, $0x781<UINT32>
0x004047fc:	sbbl %ecx, $0xffffffff<UINT8>
0x004047ff:	pushl %esi
0x00404800:	movl %esi, %edi
0x00404802:	subl %esi, %eax
0x00404804:	rep movsb %es:(%edi), %ds:(%esi)
0x00404806:	popl %esi
0x00404807:	incl %ecx
0x00404808:	incl %ecx
0x00404809:	jmp 0x00404794
0x004047d2:	movl %eax, %ebp
0x004047d4:	call 0x00404816
0x004047d9:	jmp 0x004047ff
0x00404828:	subl %edi, 0x28(%esp)
0x0040482c:	movl 0x1c(%esp), %edi
0x00404830:	popa
0x00404831:	ret $0xc<UINT16>

0x004048db:	popl %edx
0x004048dc:	popl %eax
0x004048dd:	addl %eax, 0x8(%ebx)
0x004048e0:	movl %edi, %eax
0x004048e2:	pushl %edx
0x004048e3:	movl %esi, %eax
0x004048e5:	movl %eax, -4(%esi)
0x004048e8:	addl %eax, $0x4<UINT8>
0x004048eb:	subl %esi, %eax
0x004048ed:	movl 0x8(%esi), %edx
0x004048f0:	movl %ecx, 0xc(%ebx)
0x004048f3:	movl 0x14(%esi), %ecx
0x004048f6:	call 0x00290188
0x00290188:	pushl %ebx
0x00290189:	pushl %edi
0x0029018a:	pushl %esi
0x0029018b:	pushl %ebp
0x0029018c:	call 0x00290191
0x00290191:	popl %ebp
0x00290192:	subl %ebp, $0x1000134c<UINT32>
0x00290198:	leal %esi, 0x10001343(%ebp)
0x0029019e:	movl %eax, -4(%esi)
0x002901a1:	addl %eax, $0x4<UINT8>
0x002901a4:	subl %esi, %eax
0x002901a6:	cld
0x002901a7:	movl %ebx, %esi
0x002901a9:	movl %edx, 0x8(%esi)
0x002901ac:	movl %esi, 0x1c(%esi)
0x002901af:	addl %esi, %edx
0x002901b1:	leal %edi, 0x10002f2f(%ebp)
0x002901b7:	lodsl %eax, %ds:(%esi)
0x002901b8:	stosl %es:(%edi), %eax
0x002901b9:	lodsl %eax, %ds:(%esi)
0x002901ba:	stosl %es:(%edi), %eax
0x002901bb:	lodsl %eax, %ds:(%esi)
0x002901bc:	stosl %es:(%edi), %eax
0x002901bd:	lodsl %eax, %ds:(%esi)
0x002901be:	stosl %es:(%edi), %eax
0x002901bf:	nop
0x002901c0:	cmpl 0x48(%ebx), $0x1<UINT8>
0x002901c4:	je 21
0x002901c6:	movl %esi, 0x44(%ebx)
0x002901c9:	testl %esi, %esi
0x002901cb:	je 14
0x002901cd:	movl %ecx, $0x23<UINT32>
0x002901d2:	addl %esi, %edx
0x002901d4:	movl %edi, 0x40(%ebx)
0x002901d7:	addl %edi, %edx
0x002901d9:	rep movsb %es:(%edi), %ds:(%esi)
0x002901db:	movl %esi, %ebx
0x002901dd:	leal %edi, 0x10002f1b(%ebp)
0x002901e3:	addl (%edi), %ebp
0x002901e5:	addl 0x4(%edi), %ebp
0x002901e8:	addl 0x8(%edi), %ebp
0x002901eb:	leal %ecx, 0x10002eff(%ebp)
0x002901f1:	pushl %ecx
0x002901f2:	call 0x0029033d
0x0029033d:	pushl %ebp
0x0029033e:	movl %ebp, %esp
0x00290340:	addl %esp, $0xfffffffc<UINT8>
0x00290343:	pushl %ebx
0x00290344:	pushl %edi
0x00290345:	pushl %esi
0x00290346:	call 0x0029034b
0x0029034b:	popl %ebx
0x0029034c:	subl %ebx, $0x10001506<UINT32>
0x00290352:	movl %esi, 0x8(%ebp)
0x00290355:	movl %ecx, (%esi)
0x00290357:	addl %ecx, %ebx
0x00290359:	pushl %ecx
0x0029035a:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x00290360:	movl -4(%ebp), %eax
0x00290363:	movl %edx, 0x4(%esi)
0x00290366:	movl %edi, 0x8(%esi)
0x00290369:	addl %edx, %ebx
0x0029036b:	addl %edi, %ebx
0x0029036d:	xorl %eax, %eax
0x0029036f:	addl %eax, (%edx)
0x00290371:	je 0x00290389
0x00290373:	pushl %edx
0x00290374:	movl %eax, (%edx)
0x00290376:	addl %eax, %ebx
0x00290378:	pushl %eax
0x00290379:	pushl -4(%ebp)
0x0029037c:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00290382:	stosl %es:(%edi), %eax
0x00290383:	popl %edx
0x00290384:	addl %edx, $0x4<UINT8>
0x00290387:	jmp 0x0029036d
0x00290389:	addl %esi, $0xc<UINT8>
0x0029038c:	addl %eax, (%esi)
0x0029038e:	jne 0x00290355
0x00290390:	popl %esi
0x00290391:	popl %edi
0x00290392:	popl %ebx
0x00290393:	leave
0x00290394:	ret $0x4<UINT16>

0x002901f7:	nop
0x002901f8:	nop
0x002901f9:	nop
0x002901fa:	nop
0x002901fb:	nop
0x002901fc:	nop
0x002901fd:	nop
0x002901fe:	nop
0x002901ff:	movl %ecx, 0x2c(%esi)
0x00290202:	movl 0x10002f2b(%ebp), %ecx
0x00290208:	pushl $0x4<UINT8>
0x0029020a:	pushl $0x1000<UINT32>
0x0029020f:	pushl %ecx
0x00290210:	pushl $0x0<UINT8>
0x00290212:	call VirtualAlloc@kernel32.dll
0x00290218:	movl 0x10002f27(%ebp), %eax
0x0029021e:	pushl %esi
0x0029021f:	call 0x0029061a
0x0029061a:	pushl %ebp
0x0029061b:	movl %ebp, %esp
0x0029061d:	addl %esp, $0xffffffe8<UINT8>
0x00290620:	pushl %ebx
0x00290621:	pushl %edi
0x00290622:	pushl %esi
0x00290623:	call 0x00290628
0x00290628:	popl %ebx
0x00290629:	subl %ebx, $0x100017e3<UINT32>
0x0029062f:	movl %esi, 0x8(%ebp)
0x00290632:	xorl %eax, %eax
0x00290634:	xorl %ecx, %ecx
0x00290636:	addl %ecx, 0x3c(%esi)
0x00290639:	je 10
0x0029063b:	movl %edx, 0x8(%esi)
0x0029063e:	movl %edi, %esi
0x00290640:	addl %esi, $0x50<UINT8>
0x00290643:	jmp 0x0029064c
0x0029064c:	movl -4(%ebp), %eax
0x0029064f:	movzwl %eax, 0x2(%edi)
0x00290653:	movl -16(%ebp), %eax
0x00290656:	pushl %ecx
0x00290657:	pushl %edx
0x00290658:	pushl %esi
0x00290659:	movzwl %eax, 0x10(%esi)
0x0029065d:	testl %eax, $0x10<UINT32>
0x00290662:	je 0x0029073e
0x0029073e:	popl %esi
0x0029073f:	popl %edx
0x00290740:	popl %ecx
0x00290741:	addl %esi, $0x1c<UINT8>
0x00290744:	decl %ecx
0x00290745:	jne 0x00290656
0x00290668:	pushl %esi
0x00290669:	movl %edi, 0x10002f27(%ebx)
0x0029066f:	movl -20(%ebp), %edi
0x00290672:	movl %ecx, 0x8(%esi)
0x00290675:	movl %eax, 0x14(%esi)
0x00290678:	subl %ecx, %eax
0x0029067a:	movl %esi, (%esi)
0x0029067c:	addl %esi, %edx
0x0029067e:	movl %eax, %ecx
0x00290680:	sarl %ecx, $0x2<UINT8>
0x00290683:	rep movsl %es:(%edi), %ds:(%esi)
0x00290685:	addl %ecx, %eax
0x00290687:	andl %ecx, $0x3<UINT8>
0x0029068a:	rep movsb %es:(%edi), %ds:(%esi)
0x0029068c:	popl %esi
0x0029068d:	nop
0x0029068e:	nop
0x0029068f:	nop
0x00290690:	nop
0x00290691:	nop
0x00290692:	nop
0x00290693:	nop
0x00290694:	nop
0x00290695:	nop
0x00290696:	nop
0x00290697:	nop
0x00290698:	nop
0x00290699:	nop
0x0029069a:	nop
0x0029069b:	nop
0x0029069c:	nop
0x0029069d:	nop
0x0029069e:	nop
0x0029069f:	nop
0x002906a0:	nop
0x002906a1:	nop
0x002906a2:	nop
0x002906a3:	nop
0x002906a4:	nop
0x002906a5:	nop
0x002906a6:	nop
0x002906a7:	nop
0x002906a8:	nop
0x002906a9:	nop
0x002906aa:	nop
0x002906ab:	nop
0x002906ac:	nop
0x002906ad:	nop
0x002906ae:	nop
0x002906af:	nop
0x002906b0:	nop
0x002906b1:	nop
0x002906b2:	nop
0x002906b3:	movl %eax, 0x4(%esi)
0x002906b6:	addl %eax, %edx
0x002906b8:	movl -24(%ebp), %eax
0x002906bb:	movl %eax, -16(%ebp)
0x002906be:	decl %eax
0x002906bf:	movl -12(%ebp), %eax
0x002906c2:	pushl %edx
0x002906c3:	pushl %eax
0x002906c4:	pushl 0x8(%ebp)
0x002906c7:	call 0x00290a08
0x00290a08:	pushl %ebp
0x00290a09:	movl %ebp, %esp
0x00290a0b:	addl %esp, $0xfffffffc<UINT8>
0x00290a0e:	pushl %ebx
0x00290a0f:	pushl %edi
0x00290a10:	pushl %esi
0x00290a11:	movl %ebx, 0x8(%ebp)
0x00290a14:	movl %esi, %ebx
0x00290a16:	movl %ecx, 0x30(%ebx)
0x00290a19:	subl %esi, %ecx
0x00290a1b:	movl -4(%ebp), %esi
0x00290a1e:	xorl %ecx, %ecx
0x00290a20:	lodsl %eax, %ds:(%esi)
0x00290a21:	testl %eax, %eax
0x00290a23:	je 28
0x00290a25:	cmpl %ecx, 0xc(%ebp)
0x00290a28:	je 0x00290a2d
0x00290a2d:	nop
0x00290a2e:	nop
0x00290a2f:	nop
0x00290a30:	nop
0x00290a31:	nop
0x00290a32:	nop
0x00290a33:	nop
0x00290a34:	nop
0x00290a35:	nop
0x00290a36:	nop
0x00290a37:	nop
0x00290a38:	nop
0x00290a39:	nop
0x00290a3a:	nop
0x00290a3b:	nop
0x00290a3c:	nop
0x00290a3d:	nop
0x00290a3e:	addl %eax, -4(%ebp)
0x00290a41:	popl %esi
0x00290a42:	popl %edi
0x00290a43:	popl %ebx
0x00290a44:	leave
0x00290a45:	ret $0x8<UINT16>

0x002906cc:	leal %ecx, 0x10002f2f(%ebx)
0x002906d2:	pushl %ecx
0x002906d3:	pushl -24(%ebp)
0x002906d6:	pushl -20(%ebp)
0x002906d9:	call 0x00290008
0x00290008:	pusha
0x00290009:	movl %esi, 0x24(%esp)
0x0029000d:	movl %edi, 0x28(%esp)
0x00290011:	cld
0x00290012:	lodsl %eax, %ds:(%esi)
0x00290013:	xorl %ecx, %ecx
0x00290015:	testl %eax, %eax
0x00290017:	je 17
0x00290019:	xorl %edx, %edx
0x0029001b:	leal %ebx, (%eax,%edi)
0x0029001e:	movsb %es:(%edi), %ds:(%esi)
0x0029001f:	movb %cl, $0x3<UINT8>
0x00290021:	call 0x00290098
0x00290098:	addl %edx, %edx
0x0029009a:	jne 0x002900a2
0x0029009c:	xchgl %edx, %eax
0x0029009d:	lodsl %eax, %ds:(%esi)
0x0029009e:	xchgl %edx, %eax
0x0029009f:	addl %edx, %edx
0x002900a1:	incl %edx
0x002900a2:	ret

0x00290026:	jae 0x0029001e
0x00290028:	cmpl %edi, %ebx
0x0029002a:	jae 0x002900b5
0x00290030:	pushl %ebx
0x00290031:	pushl %ebp
0x00290032:	pushl %edi
0x00290033:	xorl %ebx, %ebx
0x00290035:	incl %ebx
0x00290036:	xorl %ebp, %ebp
0x00290038:	movl %eax, %ebx
0x0029003a:	leal %edi, (%ebp,%ebx)
0x0029003e:	movl %ebp, %ebx
0x00290040:	movl %ebx, %edi
0x00290042:	call 0x00290098
0x00290047:	jae 0x0029003a
0x00290049:	leal %ebx, (%ebp,%edi)
0x0029004d:	addl %eax, %edi
0x0029004f:	movl %ebp, %edi
0x00290051:	call 0x00290098
0x00290056:	jae 0x0029003a
0x00290058:	popl %edi
0x00290059:	popl %ebp
0x0029005a:	popl %ebx
0x0029005b:	subl %eax, %ecx
0x0029005d:	jae 0x00290068
0x00290068:	movb %cl, $0x6<UINT8>
0x0029006a:	call 0x00290098
0x0029006f:	adcl %eax, %eax
0x00290071:	decl %ecx
0x00290072:	jne 0x0029006a
0x00290074:	incl %eax
0x00290075:	call 0x002900a3
0x002900a3:	xorl %ecx, %ecx
0x002900a5:	incl %ecx
0x002900a6:	call 0x00290098
0x002900ab:	adcl %ecx, %ecx
0x002900ad:	call 0x00290098
0x002900b2:	jb 0x002900a6
0x002900b4:	ret

0x0029007a:	movl %ebp, %eax
0x0029007c:	cmpl %eax, $0x8001<UINT32>
0x00290081:	sbbl %ecx, $0xffffffff<UINT8>
0x00290084:	cmpl %eax, $0x781<UINT32>
0x00290089:	sbbl %ecx, $0xffffffff<UINT8>
0x0029008c:	pushl %esi
0x0029008d:	movl %esi, %edi
0x0029008f:	subl %esi, %eax
0x00290091:	rep movsb %es:(%edi), %ds:(%esi)
0x00290093:	popl %esi
0x00290094:	incl %ecx
0x00290095:	incl %ecx
0x00290096:	jmp 0x00290021
0x0029005f:	movl %eax, %ebp
0x00290061:	call 0x002900a3
0x00290066:	jmp 0x0029008c
0x002900b5:	subl %edi, 0x28(%esp)
0x002900b9:	movl 0x1c(%esp), %edi
0x002900bd:	popa
0x002900be:	ret $0xc<UINT16>

0x002906db:	movl %ecx, %eax
0x002906dd:	incl %eax
0x002906de:	je 116
0x002906e0:	xorl %eax, %eax
0x002906e2:	addl %eax, -12(%ebp)
0x002906e5:	je 0x002906f9
0x002906f9:	popl %edx
0x002906fa:	pushl %esi
0x002906fb:	movl %edi, 0x4(%esi)
0x002906fe:	addl %edi, %edx
0x00290700:	cmpl %edi, -24(%ebp)
0x00290703:	jne 4
0x00290705:	addl %edi, %ecx
0x00290707:	jmp 0x0029071a
0x0029071a:	movl %eax, %edi
0x0029071c:	addl %eax, $0xfff<UINT32>
0x00290721:	shrl %eax, $0xc<UINT8>
0x00290724:	shll %eax, $0xc<UINT8>
0x00290727:	subl %eax, %edi
0x00290729:	movl %ecx, %eax
0x0029072b:	xorl %eax, %eax
0x0029072d:	pushl %edx
0x0029072e:	movl %edx, %ecx
0x00290730:	sarl %ecx, $0x2<UINT8>
0x00290733:	rep stosl %es:(%edi), %eax
0x00290735:	addl %ecx, %edx
0x00290737:	andl %ecx, $0x3<UINT8>
0x0029073a:	rep stosb %es:(%edi), %al
0x0029073c:	popl %edx
0x0029073d:	popl %esi
0x0029074b:	xorl %eax, %eax
0x0029074d:	popl %esi
0x0029074e:	popl %edi
0x0029074f:	popl %ebx
0x00290750:	leave
0x00290751:	ret $0x4<UINT16>

0x00290224:	leal %ecx, 0x10002dbd(%ebp)
0x0029022a:	testl %eax, %eax
0x0029022c:	jne 148
0x00290232:	pushl %esi
0x00290233:	call 0x00290578
0x00290578:	pushl %ebp
0x00290579:	movl %ebp, %esp
0x0029057b:	addl %esp, $0xffffffe8<UINT8>
0x0029057e:	pushl %ebx
0x0029057f:	pushl %edi
0x00290580:	pushl %esi
0x00290581:	call 0x00290586
0x00290586:	popl %ebx
0x00290587:	subl %ebx, $0x10001741<UINT32>
0x0029058d:	movl %esi, 0x8(%ebp)
0x00290590:	xorl %eax, %eax
0x00290592:	xorl %ecx, %ecx
0x00290594:	addl %ecx, 0x3c(%esi)
0x00290597:	je 10
0x00290599:	movl %edx, 0x8(%esi)
0x0029059c:	movl %edi, %esi
0x0029059e:	addl %esi, $0x50<UINT8>
0x002905a1:	jmp 0x002905aa
0x002905aa:	movl %eax, 0x10002f27(%ebx)
0x002905b0:	movl -4(%ebp), %eax
0x002905b3:	movl %ebx, %esi
0x002905b5:	movzwl %eax, 0x10(%ebx)
0x002905b9:	testl %eax, $0x2<UINT32>
0x002905be:	je 0x0029060d
0x002905c0:	pushl %ecx
0x002905c1:	movl %esi, 0x4(%ebx)
0x002905c4:	movl %edi, -4(%ebp)
0x002905c7:	movl %ecx, 0x8(%ebx)
0x002905ca:	addl %esi, %edx
0x002905cc:	movl %eax, %ecx
0x002905ce:	sarl %ecx, $0x2<UINT8>
0x002905d1:	rep movsl %es:(%edi), %ds:(%esi)
0x002905d3:	addl %ecx, %eax
0x002905d5:	andl %ecx, $0x3<UINT8>
0x002905d8:	rep movsb %es:(%edi), %ds:(%esi)
0x002905da:	movl %edi, 0x4(%ebx)
0x002905dd:	movl %ecx, 0x8(%ebx)
0x002905e0:	addl %edi, %edx
0x002905e2:	xorl %eax, %eax
0x002905e4:	pushl %edx
0x002905e5:	movl %edx, %ecx
0x002905e7:	sarl %ecx, $0x2<UINT8>
0x002905ea:	rep stosl %es:(%edi), %eax
0x002905ec:	addl %ecx, %edx
0x002905ee:	andl %ecx, $0x3<UINT8>
0x002905f1:	rep stosb %es:(%edi), %al
0x002905f3:	popl %edx
0x002905f4:	movl %esi, -4(%ebp)
0x002905f7:	movl %edi, (%ebx)
0x002905f9:	addl %edi, %edx
0x002905fb:	movl %ecx, 0x8(%ebx)
0x002905fe:	movl %eax, %ecx
0x00290600:	sarl %ecx, $0x2<UINT8>
0x00290603:	rep movsl %es:(%edi), %ds:(%esi)
0x00290605:	addl %ecx, %eax
0x00290607:	andl %ecx, $0x3<UINT8>
0x0029060a:	rep movsb %es:(%edi), %ds:(%esi)
0x0029060c:	popl %ecx
0x0029060d:	addl %ebx, $0x1c<UINT8>
0x00290610:	decl %ecx
0x00290611:	jne 0x002905b5
0x00290613:	popl %esi
0x00290614:	popl %edi
0x00290615:	popl %ebx
0x00290616:	leave
0x00290617:	ret $0x4<UINT16>

0x00290238:	pushl %esi
0x00290239:	call 0x00290493
0x00290493:	pushl %ebp
0x00290494:	movl %ebp, %esp
0x00290496:	addl %esp, $0xffffffe8<UINT8>
0x00290499:	pushl %ebx
0x0029049a:	pushl %edi
0x0029049b:	pushl %esi
0x0029049c:	call 0x002904a1
0x002904a1:	popl %ebx
0x002904a2:	subl %ebx, $0x1000165c<UINT32>
0x002904a8:	movl %esi, 0x8(%ebp)
0x002904ab:	xorl %eax, %eax
0x002904ad:	xorl %ecx, %ecx
0x002904af:	addl %ecx, 0x3c(%esi)
0x002904b2:	je 10
0x002904b4:	movl %edx, 0x8(%esi)
0x002904b7:	movl %edi, %esi
0x002904b9:	addl %esi, $0x50<UINT8>
0x002904bc:	jmp 0x002904c5
0x002904c5:	movl -8(%ebp), %edx
0x002904c8:	movzwl %eax, 0x10(%esi)
0x002904cc:	testl %eax, $0x200<UINT32>
0x002904d1:	jne 125
0x002904d3:	testl %eax, $0x8<UINT32>
0x002904d8:	je 0x00290567
0x00290567:	addl %esi, $0x1c<UINT8>
0x0029056a:	decl %ecx
0x0029056b:	jne 0x002904c8
0x002904de:	pushl %ecx
0x002904df:	pushl %esi
0x002904e0:	movl %edi, 0x8(%esi)
0x002904e3:	xorl %ecx, %ecx
0x002904e5:	movl -4(%ebp), %ecx
0x002904e8:	movzwl %ebx, 0x12(%esi)
0x002904ec:	movl %esi, (%esi)
0x002904ee:	addl %esi, -8(%ebp)
0x002904f1:	cmpl %ecx, %edi
0x002904f3:	jnl 0x00290529
0x002904f5:	movl %eax, (%esi)
0x002904f7:	incl %esi
0x002904f8:	movzbl %edx, %al
0x002904fb:	addl -4(%ebp), %edx
0x002904fe:	subb %al, $0xffffffe8<UINT8>
0x00290500:	movl %edx, %ebx
0x00290502:	je 0x0029050a
0x00290504:	decb %al
0x00290506:	movb %dl, %bh
0x00290508:	jne 0x00290526
0x00290526:	incl %ecx
0x00290527:	jmp 0x002904f1
0x0029050a:	movl %eax, (%esi)
0x0029050c:	cmpb %al, %dl
0x0029050e:	jne 13
0x00290510:	shrw %ax, $0x8<UINT8>
0x00290514:	roll %eax, $0x10<UINT8>
0x00290517:	xchgb %ah, %al
0x00290519:	subl %eax, %ecx
0x0029051b:	movl (%esi), %eax
0x0029051d:	addl -4(%ebp), %eax
0x00290520:	addl %esi, $0x4<UINT8>
0x00290523:	addl %ecx, $0x4<UINT8>
0x00290529:	popl %esi
0x0029052a:	popl %ecx
0x0029052b:	xorl %eax, %eax
0x0029052d:	addl %eax, 0x14(%esi)
0x00290530:	je 53
0x00290532:	cmpl %eax, -4(%ebp)
0x00290535:	je 0x00290567
0x00290571:	popl %esi
0x00290572:	popl %edi
0x00290573:	popl %ebx
0x00290574:	leave
0x00290575:	ret $0x4<UINT16>

0x0029023e:	nop
0x0029023f:	nop
0x00290240:	nop
0x00290241:	nop
0x00290242:	nop
0x00290243:	nop
0x00290244:	nop
0x00290245:	nop
0x00290246:	nop
0x00290247:	nop
0x00290248:	nop
0x00290249:	nop
0x0029024a:	nop
0x0029024b:	nop
0x0029024c:	movl %ecx, 0x34(%esi)
0x0029024f:	testl %ecx, %ecx
0x00290251:	je 137
0x00290257:	addl %ecx, 0x8(%esi)
0x0029025a:	pushl %ecx
0x0029025b:	pushl %esi
0x0029025c:	call 0x002908a8
0x002908a8:	pushl %ebp
0x002908a9:	movl %ebp, %esp
0x002908ab:	pushl %ebx
0x002908ac:	pushl %edi
0x002908ad:	pushl %esi
0x002908ae:	movl %esi, 0xc(%ebp)
0x002908b1:	movl %ebx, 0x8(%ebp)
0x002908b4:	xorl %eax, %eax
0x002908b6:	cmpl 0x10(%esi), %eax
0x002908b9:	jne 0x002908bf
0x002908bf:	addl %eax, (%esi)
0x002908c1:	je 3
0x002908c3:	addl %eax, 0x8(%ebx)
0x002908c6:	movl %ecx, 0xc(%esi)
0x002908c9:	addl %ecx, 0x8(%ebx)
0x002908cc:	movl %edi, 0x10(%esi)
0x002908cf:	testl %edi, %edi
0x002908d1:	je 3
0x002908d3:	addl %edi, 0x8(%ebx)
0x002908d6:	pushl %eax
0x002908d7:	pushl %edi
0x002908d8:	pushl %ecx
0x002908d9:	pushl %ebx
0x002908da:	call 0x002908ef
0x002908ef:	pushl %ebp
0x002908f0:	movl %ebp, %esp
0x002908f2:	addl %esp, $0xffffffe8<UINT8>
0x002908f5:	pushl %ebx
0x002908f6:	pushl %edi
0x002908f7:	pushl %esi
0x002908f8:	call 0x002908fd
0x002908fd:	popl %ebx
0x002908fe:	subl %ebx, $0x10001ab8<UINT32>
0x00290904:	movl %eax, 0xc(%ebp)
0x00290907:	movl 0x10002d9c(%ebx), %eax
0x0029090d:	xorl %eax, %eax
0x0029090f:	movl 0x10002da0(%ebx), %eax
0x00290915:	xorl %esi, %esi
0x00290917:	incl %esi
0x00290918:	incl %esi
0x00290919:	movl %edx, 0x10002eef(%ebx)
0x0029091f:	pushl 0xc(%ebp)
0x00290922:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32: API Node	
0x00290924:	movl -4(%ebp), %eax
0x00290927:	movl %edx, 0x10002f1b(%ebx)
0x0029092d:	testl %eax, %eax
0x0029092f:	jne 0x0029093e
0x0029093e:	nop
0x0029093f:	nop
0x00290940:	nop
0x00290941:	nop
0x00290942:	nop
0x00290943:	nop
0x00290944:	nop
0x00290945:	nop
0x00290946:	nop
0x00290947:	nop
0x00290948:	nop
0x00290949:	nop
0x0029094a:	nop
0x0029094b:	nop
0x0029094c:	nop
0x0029094d:	nop
0x0029094e:	nop
0x0029094f:	nop
0x00290950:	nop
0x00290951:	nop
0x00290952:	nop
0x00290953:	nop
0x00290954:	nop
0x00290955:	nop
0x00290956:	nop
0x00290957:	nop
0x00290958:	nop
0x00290959:	nop
0x0029095a:	nop
0x0029095b:	nop
0x0029095c:	nop
0x0029095d:	nop
0x0029095e:	nop
0x0029095f:	nop
0x00290960:	nop
0x00290961:	nop
0x00290962:	nop
0x00290963:	nop
0x00290964:	nop
0x00290965:	nop
0x00290966:	nop
0x00290967:	nop
0x00290968:	movl %esi, 0x10(%ebp)
0x0029096b:	movl %edi, 0x8(%ebp)
0x0029096e:	movl %edx, 0x14(%ebp)
0x00290971:	testl %edx, %edx
0x00290973:	jne 0x00290977
0x00290977:	testl %esi, %esi
0x00290979:	jne 0x0029097d
0x0029097d:	movl 0x10002da0(%ebx), $0x0<UINT32>
0x00290987:	movl %eax, (%edx)
0x00290989:	testl %eax, %eax
0x0029098b:	je 0x002909d1
0x0029098d:	pushl %edx
0x0029098e:	movl 0x10002da0(%ebx), %eax
0x00290994:	testl %eax, $0x80000000<UINT32>
0x00290999:	je 0x002909a4
0x002909a4:	movl %ecx, 0x8(%ebp)
0x002909a7:	addl %eax, 0x8(%ecx)
0x002909aa:	xorl %ecx, %ecx
0x002909ac:	movw %cx, (%eax)
0x002909af:	pushl %ecx
0x002909b0:	incl %eax
0x002909b1:	incl %eax
0x002909b2:	pushl %eax
0x002909b3:	pushl -4(%ebp)
0x002909b6:	call 0x00290ad2
0x00290ad2:	pushl %ebp
0x00290ad3:	movl %ebp, %esp
0x00290ad5:	pushl %ebx
0x00290ad6:	pushl %edi
0x00290ad7:	pushl %esi
0x00290ad8:	call 0x00290add
0x00290add:	popl %ebx
0x00290ade:	subl %ebx, $0x10001c98<UINT32>
0x00290ae4:	pushl 0xc(%ebp)
0x00290ae7:	pushl 0x8(%ebp)
0x00290aea:	call GetProcAddress@kernel32.dll
0x00290af0:	popl %esi
0x00290af1:	popl %edi
0x00290af2:	popl %ebx
0x00290af3:	leave
0x00290af4:	ret $0xc<UINT16>

0x002909bc:	popl %edx
0x002909bd:	testl %eax, %eax
0x002909bf:	je -145
0x002909c5:	movl (%esi), %eax
0x002909c7:	movl (%edx), %eax
0x002909c9:	addl %edx, $0x4<UINT8>
0x002909cc:	addl %esi, $0x4<UINT8>
0x002909cf:	jmp 0x0029097d
0x002909d1:	xorl %eax, %eax
0x002909d3:	popl %esi
0x002909d4:	popl %edi
0x002909d5:	popl %ebx
0x002909d6:	leave
0x002909d7:	ret $0x10<UINT16>

0x002908df:	incl %eax
0x002908e0:	jne 0x002908ea
0x002908ea:	addl %esi, $0x14<UINT8>
0x002908ed:	jmp 0x002908b4
GetModuleHandleA@kernel32.dll: API Node	
0x002908bb:	cmpl (%esi), %eax
0x002908bd:	je 0x002908e3
0x002908e3:	popl %esi
0x002908e4:	popl %edi
0x002908e5:	popl %ebx
0x002908e6:	leave
0x002908e7:	ret $0x8<UINT16>

0x00290261:	testl %eax, %eax
0x00290263:	je 0x002902e0
0x002902e0:	movl %edi, 0x8(%ebx)
0x002902e3:	movl %ebx, %esi
0x002902e5:	cmpl 0x48(%ebx), $0x1<UINT8>
0x002902e9:	jne 0x00290300
0x00290300:	movl %esi, %ebx
0x00290302:	nop
0x00290303:	nop
0x00290304:	nop
0x00290305:	nop
0x00290306:	nop
0x00290307:	nop
0x00290308:	nop
0x00290309:	nop
0x0029030a:	nop
0x0029030b:	nop
0x0029030c:	nop
0x0029030d:	nop
0x0029030e:	pushl %esi
0x0029030f:	call 0x00290af7
0x00290af7:	pushl %ebp
0x00290af8:	movl %ebp, %esp
0x00290afa:	addl %esp, $0xfffffffc<UINT8>
0x00290afd:	pushl %ebx
0x00290afe:	pushl %edi
0x00290aff:	pushl %esi
0x00290b00:	call 0x00290b05
0x00290b05:	popl %ebx
0x00290b06:	subl %ebx, $0x10001cc0<UINT32>
0x00290b0c:	movl %esi, 0x8(%ebp)
0x00290b0f:	movl %eax, 0x8(%esi)
0x00290b12:	addl %eax, 0x3c(%eax)
0x00290b15:	leal %edi, 0x80(%eax)
0x00290b1b:	movl %ecx, %edi
0x00290b1d:	shrl %ecx, $0xc<UINT8>
0x00290b20:	shll %ecx, $0xc<UINT8>
0x00290b23:	pushl %ecx
0x00290b24:	leal %eax, -4(%ebp)
0x00290b27:	pushl %eax
0x00290b28:	pushl $0x4<UINT8>
0x00290b2a:	pushl $0x1000<UINT32>
0x00290b2f:	pushl %ecx
0x00290b30:	call VirtualProtect@kernel32
VirtualProtect@kernel32: API Node	
0x00290b36:	movl %edx, 0x34(%esi)
0x00290b39:	movl (%edi), %edx
0x00290b3b:	popl %ecx
0x00290b3c:	leal %eax, -4(%ebp)
0x00290b3f:	pushl %eax
0x00290b40:	pushl -4(%ebp)
0x00290b43:	pushl $0x1000<UINT32>
0x00290b48:	pushl %ecx
0x00290b49:	call VirtualProtect@kernel32
0x00290b4f:	popl %esi
0x00290b50:	popl %edi
0x00290b51:	popl %ebx
0x00290b52:	leave
0x00290b53:	ret $0x4<UINT16>

0x00290314:	nop
0x00290315:	nop
0x00290316:	nop
0x00290317:	nop
0x00290318:	nop
0x00290319:	nop
0x0029031a:	pushl %edi
0x0029031b:	call 0x00290a48
0x00290a48:	pushl %ebp
0x00290a49:	movl %ebp, %esp
0x00290a4b:	addl %esp, $0xfffffffc<UINT8>
0x00290a4e:	pushl %ebx
0x00290a4f:	pushl %edi
0x00290a50:	pushl %esi
0x00290a51:	call 0x00290a56
0x00290a56:	popl %ebx
0x00290a57:	subl %ebx, $0x10001c11<UINT32>
0x00290a5d:	movl %eax, 0x8(%ebp)
0x00290a60:	addl %eax, 0x3c(%eax)
0x00290a63:	xorl %ecx, %ecx
0x00290a65:	movw %cx, 0x14(%eax)
0x00290a69:	leal %edi, 0x18(%ecx,%eax)
0x00290a6d:	addl %edi, $0x27<UINT8>
0x00290a70:	movl %ecx, %edi
0x00290a72:	shrl %ecx, $0xc<UINT8>
0x00290a75:	shll %ecx, $0xc<UINT8>
0x00290a78:	pushl %ecx
0x00290a79:	leal %eax, -4(%ebp)
0x00290a7c:	pushl %eax
0x00290a7d:	pushl $0x4<UINT8>
0x00290a7f:	pushl $0x1000<UINT32>
0x00290a84:	pushl %ecx
0x00290a85:	call VirtualProtect@kernel32
0x00290a8b:	movb %al, (%edi)
0x00290a8d:	testb %al, $0xffffff80<UINT8>
0x00290a8f:	je 4
0x00290a91:	subb %al, $0xffffff80<UINT8>
0x00290a93:	movb (%edi), %al
0x00290a95:	popl %ecx
0x00290a96:	leal %eax, -4(%ebp)
0x00290a99:	pushl %eax
0x00290a9a:	pushl -4(%ebp)
0x00290a9d:	pushl $0x1000<UINT32>
0x00290aa2:	pushl %ecx
0x00290aa3:	call VirtualProtect@kernel32
0x00290aa9:	popl %esi
0x00290aaa:	popl %edi
0x00290aab:	popl %ebx
0x00290aac:	leave
0x00290aad:	ret $0x4<UINT16>

0x00290320:	pushl $0x8000<UINT32>
0x00290325:	pushl $0x0<UINT8>
0x00290327:	pushl 0x10002f27(%ebp)
0x0029032d:	call VirtualFree@kernel32.dll
VirtualFree@kernel32.dll: API Node	
0x00290333:	movl %eax, 0xc(%esi)
0x00290336:	addl %eax, %edi
0x00290338:	popl %ebp
0x00290339:	popl %esi
0x0029033a:	popl %edi
0x0029033b:	popl %ebx
0x0029033c:	ret

0x004048f8:	movl 0x1000133f(%ebp), %eax
0x004048fe:	movl %esi, %eax
0x00404900:	movl %ecx, 0x14(%ebx)
0x00404903:	popl %edx
0x00404904:	jmp 0x00404912
0x00404912:	movl %eax, %esi
0x00404914:	popl %edx
0x00404915:	popl %esi
0x00404916:	popl %edi
0x00404917:	popl %ecx
0x00404918:	popl %ebx
0x00404919:	popl %ebp
0x0040491a:	jmp 0x00401000
0x00401000:	pushl %eax
0x00401001:	pushl %ebx
0x00401002:	leal %eax, 0x403098
0x00401008:	pushl %eax
0x00401009:	call 0x004011f2
0x004011f2:	jmp SetCurrentDirectoryA@kernel32.dll
SetCurrentDirectoryA@kernel32.dll: API Node	
0x0040100e:	leal %eax, 0x40306d
0x00401014:	pushl $0x0<UINT8>
0x00401016:	pushl $0x80<UINT32>
0x0040101b:	pushl $0x2<UINT8>
0x0040101d:	pushl $0x0<UINT8>
0x0040101f:	pushl $0x1<UINT8>
0x00401021:	pushl $0x40000000<UINT32>
0x00401026:	pushl %eax
0x00401027:	call 0x00401180
0x00401180:	jmp CreateFileA@kernel32.dll
CreateFileA@kernel32.dll: API Node	
0x0040102c:	movl %ebx, %eax
0x0040102e:	pushl $0x0<UINT8>
0x00401030:	pushl $0x0<UINT8>
0x00401032:	pushl $0xa<UINT8>
0x00401034:	leal %eax, 0x403008
0x0040103a:	pushl %eax
0x0040103b:	pushl %ebx
0x0040103c:	call 0x004011fe
0x004011fe:	jmp WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x00401041:	pushl %ebx
0x00401042:	call 0x00401174
0x00401174:	jmp CloseHandle@kernel32.dll
CloseHandle@kernel32.dll: API Node	
0x00401047:	pushl $0x0<UINT8>
0x00401049:	leal %eax, 0x403080
0x0040104f:	pushl %eax
0x00401050:	leal %eax, 0x403076
0x00401056:	pushl %eax
0x00401057:	call 0x0040117a
0x0040117a:	jmp CopyFileA@kernel32.dll
CopyFileA@kernel32.dll: API Node	
0x0040105c:	leal %eax, 0x403090
0x00401062:	pushl %eax
0x00401063:	leal %eax, 0x40308a
0x00401069:	pushl %eax
0x0040106a:	call 0x00401198
0x00401198:	jmp FindFirstFileA@kernel32.dll
FindFirstFileA@kernel32.dll: API Node	
0x0040106f:	leal %ebx, 0x403090
0x00401075:	pushl %ebx
0x00401076:	pushl %eax
0x00401077:	call 0x0040119e
0x0040119e:	jmp FindNextFileA@kernel32.dll
FindNextFileA@kernel32.dll: API Node	
0x0040107c:	pushl %eax
0x0040107d:	call 0x00401192
0x00401192:	jmp FindClose@kernel32.dll
FindClose@kernel32.dll: API Node	
0x00401082:	leal %eax, 0x40306d
0x00401088:	pushl %eax
0x00401089:	call 0x004011b0
0x004011b0:	jmp GetFileAttributesA@kernel32.dll
GetFileAttributesA@kernel32.dll: API Node	
0x0040108e:	pushl $0x80<UINT32>
0x00401093:	leal %eax, 0x40306d
0x00401099:	pushl %eax
0x0040109a:	call 0x004011f8
0x004011f8:	jmp SetFileAttributesA@kernel32.dll
SetFileAttributesA@kernel32.dll: API Node	
0x0040109f:	leal %eax, 0x40306d
0x004010a5:	pushl %eax
0x004010a6:	call 0x004011b0
0x004010ab:	leal %eax, 0x40306d
0x004010b1:	pushl %eax
0x004010b2:	call 0x00401186
0x00401186:	jmp DeleteFileA@kernel32.dll
DeleteFileA@kernel32.dll: API Node	
0x004010b7:	leal %eax, 0x403060
0x004010bd:	pushl %eax
0x004010be:	call 0x004011ce
0x004011ce:	jmp GetStartupInfoA@kernel32.dll
GetStartupInfoA@kernel32.dll: API Node	
0x004010c3:	call 0x004011b6
0x004011b6:	jmp GetLastError@kernel32.dll
GetLastError@kernel32.dll: API Node	
0x004010c8:	call 0x004011e0
0x004011e0:	jmp GetVersion@kernel32.dll
GetVersion@kernel32.dll: API Node	
0x004010cd:	leal %ebx, 0x403042
0x004010d3:	pushl $0x64<UINT8>
0x004010d5:	pushl %ebx
0x004010d6:	call 0x004011d4
0x004011d4:	jmp GetSystemDirectoryA@kernel32.dll
GetSystemDirectoryA@kernel32.dll: API Node	
0x004010db:	leal %ebx, 0x403042
0x004010e1:	pushl %ebx
0x004010e2:	pushl $0x64<UINT8>
0x004010e4:	call 0x004011aa
0x004011aa:	jmp GetCurrentDirectoryA@kernel32.dll
GetCurrentDirectoryA@kernel32.dll: API Node	
0x004010e9:	leal %ebx, 0x403042
0x004010ef:	pushl $0x64<UINT8>
0x004010f1:	pushl %ebx
0x004010f2:	call 0x004011e6
0x004011e6:	jmp GetWindowsDirectoryA@kernel32.dll
GetWindowsDirectoryA@kernel32.dll: API Node	
0x004010f7:	call 0x004011a4
0x004011a4:	jmp GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x004010fc:	leal %eax, 0x403054
0x00401102:	pushl %eax
0x00401103:	call 0x004011da
0x004011da:	jmp GetSystemTime@kernel32.dll
GetSystemTime@kernel32.dll: API Node	
0x00401108:	leal %eax, 0x403022
0x0040110e:	pushl %eax
0x0040110f:	call 0x004011ec
0x004011ec:	jmp LoadLibraryA@kernel32.dll
0x00401114:	leal %eax, 0x403015
0x0040111a:	pushl %eax
0x0040111b:	call 0x004011ec
0x00401120:	leal %ebx, 0x40302d
0x00401126:	pushl %ebx
0x00401127:	pushl %eax
0x00401128:	call 0x004011c8
0x004011c8:	jmp GetProcAddress@kernel32.dll
0x0040112d:	leal %ebx, 0x403042
0x00401133:	pushl %ebx
0x00401134:	pushl $0x64<UINT8>
0x00401136:	call 0x004011aa
0x0040113b:	pushl $0x64<UINT8>
0x0040113d:	pushl $0x403000<UINT32>
0x00401142:	pushl $0x0<UINT8>
0x00401144:	call 0x004011bc
0x004011bc:	jmp GetModuleFileNameA@kernel32.dll
GetModuleFileNameA@kernel32.dll: API Node	
0x00401149:	leal %eax, 0x403008
0x0040114f:	movl %ebx, $0x0<UINT32>
0x00401154:	pushl %ebx
0x00401155:	pushl %eax
0x00401156:	pushl %eax
0x00401157:	pushl %ebx
0x00401158:	call 0x00401204
0x00401204:	jmp MessageBoxA@user32.dll
MessageBoxA@user32.dll: API Node	
0x0040115d:	pushl $0x0<UINT8>
0x0040115f:	call 0x004011c2
0x004011c2:	jmp GetModuleHandleA@kernel32.dll
0x00401164:	addl %eax, $0x116b<UINT32>
0x00401169:	pushl %eax
0x0040116a:	ret

0x0040116b:	addl %eax, %ebx
0x0040116d:	popl %eax
0x0040116e:	call 0x0040118c
0x0040118c:	jmp ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
